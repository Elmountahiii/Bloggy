package com.redgunner.bloggy.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redgunner.bloggy.models.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticlesDao {


    @Insert
    suspend fun addArticles(articles:List<Article>)

    //inserting
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)


    //retrieving
    @Query("SELECT * FROM ArticlesTable ")
   suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM ArticlesTable WHERE articleSaved==1 ORDER BY id DESC")
    fun getSavedArticles(): Flow<List<Article>>

    @Query("SELECT * FROM ArticlesTable WHERE articleSeen==1 ORDER BY id ASC")
    fun getHistoryArticles(): Flow<List<Article>>

    @Query("SELECT * FROM ArticlesTable ORDER BY RANDOM() LIMIT 1")
    suspend fun getRecommendedArticle():Article?


    @Query("SELECT * FROM ArticlesTable WHERE id =:articleId")
    suspend fun getArticle(articleId: Int): Article

    @Query("SELECT * FROM ArticlesTable WHERE category = :category")
    suspend fun getArticlesByCategory(category: String): List<Article>

    //searching
    @Query("SELECT * FROM ArticlesTable WHERE title LIKE :searchQuery ")
    suspend fun search(searchQuery: String): List<Article>


    //deleting
    @Query("DELETE FROM ArticlesTable")
    suspend fun deleteArticles()

    @Query("DELETE FROM ArticlesTable WHERE title =:title ")
    suspend fun deleteArticle(title: String)



}