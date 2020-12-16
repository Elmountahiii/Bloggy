package com.redgunner.bloggy.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.redgunner.bloggy.models.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    //inserting
    @Insert
    fun addCategory(category:List<Category>)


    //retrieving
    @Query("SELECT * FROM CATEGORYTABLE")
     fun getAllCategory():Flow<List<Category>>

    //deleting
    @Query("DELETE FROM CategoryTable")
    suspend fun deleteCategory()

}