package com.skydevices.mobnews.presenter.news

import com.skydevices.mobnews.model.NewsResponse
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) :
    NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
    }

    override fun requestTopAll() {
        this.view.showProgressBar()
        this.dataSource.getTopBreakingNews(this)
    }

    override fun onSucess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)

    }

    override fun onTopSuccess(newsResponse: NewsResponse) {
        this.view.showTopArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailule(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}