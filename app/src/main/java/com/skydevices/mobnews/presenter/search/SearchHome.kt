package com.skydevices.mobnews.presenter.search

import com.skydevices.mobnews.model.NewsResponse

interface SearchHome {
    interface Presenter{
        fun search(term: String)
        fun onSucess(newsResponse: NewsResponse)
        fun onError(message : String)
        fun onComplete()
    }
}