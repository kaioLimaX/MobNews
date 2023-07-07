package com.skydevices.mobnews.model.data

import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.model.db.ArticleDatabase

class NewsReposity(private val db: ArticleDatabase) {

    fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

        fun getAll(): (List<Article>) = db.getArticleDao().getAll()

    fun delete(article: Article) = db.getArticleDao().delete(article)

    }

