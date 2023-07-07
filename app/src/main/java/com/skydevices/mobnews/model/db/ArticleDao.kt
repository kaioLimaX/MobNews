package com.skydevices.mobnews.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skydevices.mobnews.model.Article

@Dao
interface ArticleDao {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    fun updateInsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

    @Delete
    fun delete(article: Article)

}

