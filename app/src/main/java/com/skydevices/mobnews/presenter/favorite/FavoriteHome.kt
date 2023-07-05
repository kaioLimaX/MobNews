package com.skydevices.mobnews.presenter.favorite

import com.skydevices.mobnews.model.Article

interface FavoriteHome {
    fun showArticles(article: List<Article>)
}