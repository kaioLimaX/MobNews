package com.skydevices.mobnews.model.data

import android.content.Context
import android.util.Log
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.db.ArticleDatabase
import com.skydevices.mobnews.network.RetrofitInstrance
import com.skydevices.mobnews.presenter.favorite.FavoriteHome
import com.skydevices.mobnews.presenter.news.NewsHome
import com.skydevices.mobnews.presenter.search.SearchHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsReposity: NewsReposity = NewsReposity(db)

    @DelicateCoroutinesApi
    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstrance.api.getBreakingNews("us")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    Log.d("getBreakingNews", newsResponse.toString())
                    callback.onSucess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    @DelicateCoroutinesApi
    fun getTopBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstrance.api.searchNews("youtube")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    Log.d("searchNews", "$newsResponse")

                    callback.onTopSuccess(newsResponse)
                }
                callback.onComplete()

            } else {
                callback.onError(response.message())
                callback.onComplete()

            }
        }
    }


    @DelicateCoroutinesApi
    fun searchNew(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstrance.api.searchNews(term)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    Log.d("searchNews", "$newsResponse")

                    callback.onSucess(newsResponse)
                }
                callback.onComplete()

            } else {
                callback.onError(response.message())
                callback.onComplete()

            }
        }

    }


    fun saveArticle(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            newsReposity.updateInsert(article)
        }
    }

    fun getAllArticle(callback: FavoriteHome.Presenter) {
        var allArticle: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticle = newsReposity.getAll()

            with(Dispatchers.Main) {
                callback.onSuccess(allArticle)
            }
        }
    }


    fun deleteArticle(article: Article?) {
        CoroutineScope(Dispatchers.IO).launch {
            article?.let { articleSafe ->
                newsReposity.delete(articleSafe)

            }
        }
    }


}