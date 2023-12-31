package com.skydevices.mobnews.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.adapter.MainAdapter
import com.skydevices.mobnews.databinding.ActivitySearchBinding
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome
import com.skydevices.mobnews.presenter.search.SearchPresenter
import com.skydevices.mobnews.util.Constants
import com.skydevices.mobnews.util.UtilQueryTextListiner

class SearchActivity : AbstractActivity(), ViewHome.View {

    private lateinit var binding: ActivitySearchBinding

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: SearchPresenter

    override fun getLayout(): ViewBinding {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        supportActionBar?.title = "Pesquisar"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()


    }

    private fun search() {

        binding.searchView.setOnQueryTextListener(
            UtilQueryTextListiner(
                this.lifecycle
            ) { newText ->
                newText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        binding.rvProgressBarSearch.visibility = View.VISIBLE
                    } else {
                        mainAdapter.differ.submitList(null)
                    }


                }
            }
        )
    }


    private fun configRecycle() {
        setContentView(binding.root)
        with(binding.rvSearch) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)

        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)

        }
    }

    override fun showProgressBar() {
        binding.rvProgressBarSearch.visibility = View.VISIBLE

    }

    override fun showFailule(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(article: List<Article>) {
        mainAdapter.differ.submitList(article.toList())

    }

    override fun showTopArticles(article: List<Article>) {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Constants.finishWithFadeTransition(this)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Constants.finishWithFadeTransition(this)
    }

}