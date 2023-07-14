package com.skydevices.mobnews.ui

import android.util.Log
import android.webkit.WebViewClient
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.skydevices.mobnews.R
import com.skydevices.mobnews.databinding.ActivityArticleBinding
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome
import com.skydevices.mobnews.presenter.favorite.FavoritePresenter
import com.skydevices.mobnews.util.Constants.Companion.finishWithFadeTransition

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var article: Article

    private lateinit var presenter: FavoritePresenter

    private lateinit var binding: ActivityArticleBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityArticleBinding.inflate(layoutInflater)
        return binding
    }


    override fun onInject() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this,dataSource)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.apply {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            webViewClient = WebViewClient()
            article.url?.let { url ->
                Log.d("teste", "URL: $url")
                loadUrl(url)

            }
        }

        binding.fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(
                it,
                R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finishWithFadeTransition(this)
        return true
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finishWithFadeTransition(this)
    }

    override fun showArticles(article: List<Article>) {}


}