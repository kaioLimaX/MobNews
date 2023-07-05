package com.skydevices.mobnews.model.data

import android.util.Log
import com.skydevices.mobnews.network.RetrofitInstrance
import com.skydevices.mobnews.presenter.news.NewsHome
import com.skydevices.mobnews.presenter.search.SearchHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstrance.api.getBreakingNews("us")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    Log.d("getBreakingNews", "${newsResponse.toString()}")
                    callback.onSucess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNew(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstrance.api.searchNews(term)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    Log.d("searchNews", "${newsResponse.toString()}")

                    callback.onSucess(newsResponse)
                }
                callback.onComplete()

            } else {
                callback.onError(response.message())
                callback.onComplete()

            }
        }

    }


}