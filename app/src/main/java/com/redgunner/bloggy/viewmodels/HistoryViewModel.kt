package com.redgunner.bloggy.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.redgunner.bloggy.repository.ArticleRepository
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor(
   private val articleRepository: ArticleRepository
) : ViewModel() {


    val historyList = articleRepository.historyArticles.asLiveData()




    fun markArticle(articleId:Int,state:Boolean){
        viewModelScope.launch {
            articleRepository.markArticle(articleId,state)
        }
    }
}