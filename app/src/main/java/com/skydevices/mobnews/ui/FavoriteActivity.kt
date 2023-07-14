package com.skydevices.mobnews.ui

import android.content.Intent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.skydevices.mobnews.R
import com.skydevices.mobnews.adapter.FavoriteAdapter
import com.skydevices.mobnews.databinding.ActivityFavoriteBinding
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.data.NewsDataSource
import com.skydevices.mobnews.presenter.ViewHome
import com.skydevices.mobnews.presenter.favorite.FavoritePresenter
import com.skydevices.mobnews.util.Constants

class FavoriteActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteAdapter by lazy {
        FavoriteAdapter()
    }

    private lateinit var presenter: FavoritePresenter


    override fun getLayout(): ViewBinding {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        supportActionBar?.title = "Favoritos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getFavoriteArticle()
        configRecycle()
        clickAdapter()

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = favoriteAdapter.differ.currentList[position]
                var isUndoClicked = false
                presenter.deleteArticle(article)



                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful,
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        favoriteAdapter.notifyDataSetChanged()
                        isUndoClicked = true

                    }

                    addCallback(object : Snackbar.Callback() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)
                            if (!isUndoClicked) {
                                val updatedList = favoriteAdapter.differ.currentList.toMutableList()
                                updatedList.remove(article)
                                favoriteAdapter.differ.submitList(updatedList)
                            }
                        }
                    })

                    show()
                }


            }

        }
        ItemTouchHelper(itemTouchCallback).apply {
            attachToRecyclerView(binding.rvFavorite)
        }

        presenter.getFavoriteArticle()

    }

    private fun configRecycle() {

        setContentView(binding.root)
        with(binding.rvFavorite) {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)

        }
    }

    private fun clickAdapter() {
        favoriteAdapter.setOnclickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)

        }
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

    override fun showArticles(article: List<Article>) {
        runOnUiThread {
            favoriteAdapter.differ.submitList(article.toList())
        }

    }

}