package com.redgunner.bloggy.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.repository.ArticleRepository
import kotlinx.coroutines.launch

class SearchViewModel@ViewModelInject
constructor( private val articleRepository: ArticleRepository) : ViewModel()  {


    val searchList=MutableLiveData<List<Article>>()


    fun search(searchQuery:String){

        viewModelScope.launch {
             searchList.value=articleRepository.search(searchQuery)
        }
    }


    fun markArticle(articleId:Int,state:Boolean){
        viewModelScope.launch {
            articleRepository.markArticle(articleId,state)
        }
    }





}