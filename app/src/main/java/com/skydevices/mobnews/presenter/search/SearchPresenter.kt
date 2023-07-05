package com.skydevices.mobnews.presenter.search

import com.skydevices.mobnews.model.NewsResponse
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome

class SearchPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : SearchHome.Presenter {
    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNew(term, this)

    }

    override fun onSucess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)

    }

    override fun onError(message: String) {
        this.view.showFailule(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}