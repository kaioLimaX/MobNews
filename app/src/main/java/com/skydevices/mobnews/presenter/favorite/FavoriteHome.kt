package com.skydevices.mobnews.presenter.favorite

import com.skydevices.mobnews.model.Article

interface FavoriteHome {
    interface Presenter {
        fun onSuccess(article: List<Article>)

    }

}