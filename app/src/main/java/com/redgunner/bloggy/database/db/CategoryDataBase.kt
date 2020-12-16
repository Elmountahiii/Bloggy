package com.redgunner.bloggy.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.redgunner.bloggy.database.dao.CategoryDao
import com.redgunner.bloggy.models.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/*
* build the Category table or Category database
*
* */

@Database(entities = [Category::class], version = 1)
abstract class CategoryDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao


    class CategoryDatabaseCallBack() : RoomDatabase.Callback() {


        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val scope = CoroutineScope(SupervisorJob())

           Instance?.let { db ->


                scope.launch {
                    populateDatabase(db.categoryDao())
                }
            }


        }

        private suspend fun populateDatabase(categoryDao: CategoryDao) {
             categoryDao.deleteCategory()

            // Add dummy category to local database just for testing purposes

            val categorys= listOf(Category("World"),Category("Business"), Category("Tech"),Category("Science"),Category("Health"),Category("Entertainment"))


            categoryDao.addCategory(categorys)




        }

    }


    companion object {
        private var Instance: CategoryDataBase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = Instance ?: synchronized(LOCK) {
            Instance ?: buildDatabase(context).also {
                Instance = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CategoryDataBase::class.java,
            "Category.db"
        ).fallbackToDestructiveMigration()
            .addCallback(CategoryDatabaseCallBack())
            .build()

    }
}