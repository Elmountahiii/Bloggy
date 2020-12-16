package com.redgunner.bloggy.di

import android.content.Context
import com.redgunner.bloggy.database.dao.ArticlesDao
import com.redgunner.bloggy.database.dao.CategoryDao
import com.redgunner.bloggy.database.db.ArticlesDataBase
import com.redgunner.bloggy.database.db.CategoryDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


/*
* this class is a hilt component for building our databases
*
* */
@InstallIn(ApplicationComponent::class)
@Module
class DataBaseModel {

    @Singleton
    @Provides
    fun provideArticleDataBase(@ApplicationContext context: Context): ArticlesDataBase {
        return ArticlesDataBase.invoke(context)
    }


    @Singleton
    @Provides
    fun provideCategoryDataBase(@ApplicationContext context: Context): CategoryDataBase {

        return CategoryDataBase.invoke(context)
    }

    @Singleton
    @Provides
    fun provideCategoryDAO(categoryDataBase: CategoryDataBase): CategoryDao {
        return categoryDataBase.categoryDao()
    }


    @Singleton
    @Provides
    fun provideArticleDAO(articlesDataBase: ArticlesDataBase): ArticlesDao {
        return articlesDataBase.articleDAO()
    }






}