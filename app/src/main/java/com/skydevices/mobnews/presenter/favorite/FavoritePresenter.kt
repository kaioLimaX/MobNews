package com.skydevices.mobnews.presenter.favorite

import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome

class FavoritePresenter(
    val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource):FavoriteHome.Presenter {

    fun getFavoriteArticle(){
        this.dataSource.getAllArticle(this)
    }
    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article){
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(article: List<Article>) {
        this.view.showArticles(article)
    }


}