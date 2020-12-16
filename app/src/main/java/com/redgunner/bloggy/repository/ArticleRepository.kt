package com.redgunner.bloggy.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.redgunner.bloggy.database.dao.ArticlesDao
import com.redgunner.bloggy.database.dao.CategoryDao
import com.redgunner.bloggy.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val categoryDao: CategoryDao
) {


    //real list of local articles
   // val listOfArticles=articlesDao.getAllArticles()
    val categoryList = categoryDao.getAllCategory()
    val savedList = articlesDao.getSavedArticles()
    val historyArticles = articlesDao.getHistoryArticles()
    val recommendedArticle=MutableLiveData<Article?>()




    /*
    * use this function to call your api or firebase ...... to get new article
    * right now i will not call any api or use any cloud database to get article  it depends on your preference
    * i will use dummy article just to show you app how its works  to have clear ide how the app works
    * you can call this function on app start every time to get new  articles and refresh old local database
    * or use this function whenever the use try to refresh the ui its depends on you and how you want your app to be
    * */
    fun callYourAPI() {


    }





    /*
    * this function get the article to read
    * */
    suspend fun getArticle(articleId: Int):Article{
        return articlesDao.getArticle(articleId)
    }


    suspend fun getAllArticles():List<Article>{
        return articlesDao.getAllArticles()
    }


    suspend fun getRecommendedArticle(){

        recommendedArticle.value=articlesDao.getRecommendedArticle()
    }









    /*
    * this function is for search for articles
    * get searchQuery and return list of articles that contains that searchQuery
    * */

    suspend fun search(searchQuery: String = ""): List<Article> {

        return articlesDao.search("%$searchQuery%")


    }


    /*
    * this function is for getting similar articles when the user read some article
    * */
    suspend fun getSimilarArticles(category:String):List<Article>{

        return articlesDao.getArticlesByCategory(category)

    }


    /*
    * this function add check mark to article to indicate that this article is saved
    * */
    suspend fun markArticle(articleId: Int, state: Boolean) {

        val article = articlesDao.getArticle(articleId)

        if (!article.articleSaved==state){
            article.articleSaved = state

            articlesDao.addArticle(article)
        }




    }


    /*
    * when user read some article this function add the article to history list
    *  */
    suspend fun addArticleToHistory(articleId: Int){

        val article=articlesDao.getArticle(articleId)

        article.articleSeen=true

        articlesDao.addArticle(article)


    }


    /*
    * this function for getting all article that has same category
    * */
    suspend fun getArticlesByCategory(category: String):List<Article>{
        return articlesDao.getArticlesByCategory(category)
    }



}