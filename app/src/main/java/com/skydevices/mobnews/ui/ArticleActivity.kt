package com.skydevices.mobnews.ui

import android.util.Log
import android.webkit.WebViewClient
import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.databinding.ActivityArticleBinding
import com.skydevices.mobnews.model.Article

class ArticleActivity : AbstractActivity() {

    private lateinit var article: Article

    private lateinit var binding: ActivityArticleBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityArticleBinding.inflate(layoutInflater)
        return binding
    }


    override fun onInject() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        getArticle()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.apply {

            webViewClient = WebViewClient()
            article.url?.let { url ->
                Log.d("teste", "URL: $url")
                loadUrl(url)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finishWithFadeTransition()
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishWithFadeTransition()
    }

    private fun finishWithFadeTransition() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}