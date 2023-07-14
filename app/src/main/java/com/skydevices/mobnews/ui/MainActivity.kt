package com.skydevices.mobnews.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.R
import com.skydevices.mobnews.adapter.MainAdapter
import com.skydevices.mobnews.adapter.MainNewsAdapter
import com.skydevices.mobnews.databinding.ActivityMainBinding
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome
import com.skydevices.mobnews.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    private lateinit var binding: ActivityMainBinding


    private val mainAdapter by lazy {
        MainAdapter()
    }

    private val mainNewsAdapter by lazy {
        MainNewsAdapter()
    }
    private lateinit var presenter: NewsPresenter

    private lateinit var presenterTopNews: NewsPresenter


    override fun getLayout(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        binding = ActivityMainBinding.inflate(layoutInflater)

        val dataSource = NewsDataSource(this)
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        presenterTopNews = NewsPresenter(this, dataSource)
        presenterTopNews.requestTopAll()
        configRecycle()
        clickAdapter()
        clickAdapterNews()

    }

    private fun configRecycle() {

        setContentView(binding.root)

        with(binding.rvNews) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }
        with(binding.rvMainNews) {
            adapter = mainNewsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)

        }



    }



    private fun clickAdapter() {
        mainAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)

        }
    }

    private fun clickAdapterNews() {
        mainNewsAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)

        }
    }

    override fun showProgressBar() {
        binding.rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailule(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(article: List<Article>) {
        mainAdapter.differ.submitList(article.toList())

    }

    override fun showTopArticles(article: List<Article>) {
        mainNewsAdapter.differ.submitList(article.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_menu -> {
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }

            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).apply {
                    startActivity(this)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

