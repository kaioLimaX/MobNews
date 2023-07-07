package com.skydevices.mobnews.presenter.favorite

import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource

class FavoritePresenter(private val dataSource: NewsDataSource) {

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }
}