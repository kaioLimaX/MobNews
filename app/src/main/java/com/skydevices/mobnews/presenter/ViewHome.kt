package com.skydevices.mobnews.presenter

import com.skydevices.mobnews.model.Article

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailule(message: String)
        fun hideProgressBar()
        fun showArticles(article: List<Article>)
        fun showTopArticles(article: List<Article>)

    }

    interface Favorite{
        fun showArticles(article: List<Article>)
    }


}