package com.redgunner.bloggy.utils

sealed class ArticleClickState {

    class MarkClick(val ArticleId: Int,val CheckedState:Boolean) : ArticleClickState()
    class ReadClick(val ArticleId: Int) : ArticleClickState()
}
