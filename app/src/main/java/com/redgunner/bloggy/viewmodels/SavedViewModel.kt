package com.redgunner.bloggy.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.repository.ArticleRepository
import kotlinx.coroutines.launch

class SavedViewModel@ViewModelInject constructor(private val articleRepository: ArticleRepository
)  : ViewModel() {

    val savedList=articleRepository.savedList.asLiveData()

    fun markArticle(articleId:Int,state:Boolean){
        viewModelScope.launch {
            articleRepository.markArticle(articleId,state)
        }
    }



}