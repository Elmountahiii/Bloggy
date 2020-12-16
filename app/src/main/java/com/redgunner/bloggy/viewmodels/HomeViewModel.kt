package com.redgunner.bloggy.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.repository.ArticleRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject
constructor(private val articleRepository: ArticleRepository) : ViewModel() {

  //  val listOfArticle=articleRepository.listOfArticles.asLiveData()

    val articlesList=MutableLiveData<List<Article>>()

    val categoryList=articleRepository.categoryList.asLiveData()

    val recommendedArticle=articleRepository.recommendedArticle


    fun markArticle(articleId:Int,state:Boolean){
        viewModelScope.launch {
            articleRepository.markArticle(articleId,state)
        }
    }

    fun getRecommendedArticle(){
        viewModelScope.launch {
            articleRepository.getRecommendedArticle()
        }
    }

    fun getArticles(){
        viewModelScope.launch {
            articlesList.value=articleRepository.getAllArticles()
        }
    }


    fun getArticlesByCategory(category:String){
        viewModelScope.launch {
            if (category == "All"){
                articlesList.value=articleRepository.getAllArticles()
            }else{
                articlesList.value=articleRepository.getArticlesByCategory(category)

            }
        }
    }





}