package com.skydevices.mobnews.presenter.news

import com.skydevices.mobnews.model.NewsResponse

interface NewsHome {

    interface Presenter{
        fun requestAll()
        fun onSucess(newsResponse: NewsResponse)
        fun onError(message : String)
        fun onComplete()
    }
}