package com.redgunner.bloggy.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redgunner.bloggy.models.Article
import com.redgunner.bloggy.repository.ArticleRepository
import kotlinx.coroutines.launch

class ReadingViewModel @ViewModelInject
constructor(private val articleRepository: ArticleRepository) : ViewModel() {


    val article = MutableLiveData<Article>()

    val similarArticles = MutableLiveData<List<Article>>()


    fun getArticle(articleId: Int) {
        viewModelScope.launch {
            article.value = articleRepository.getArticle(articleId)
        }
    }

    fun getSimilarArticles(category: String) {
        viewModelScope.launch {
            similarArticles.value = articleRepository.getArticlesByCategory(category)
        }
    }

    fun markArticle(articleId: Int, state: Boolean) {
        viewModelScope.launch {
            articleRepository.markArticle(articleId, state)
        }
    }

    fun addArticleToHistory(articleId: Int) {
        viewModelScope.launch {
            articleRepository.addArticleToHistory(articleId)
        }
    }


}