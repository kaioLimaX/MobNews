package com.skydevices.mobnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skydevices.mobnews.R
import com.skydevices.mobnews.databinding.ItemFavoritosBinding
import com.skydevices.mobnews.model.Article
import com.skydevices.mobnews.util.UtilDateTime

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(private val binding: ItemFavoritosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            //   Log.d("TesteELa",article.toString())
            Glide.with(binding.root)
                .load(article.urlToImage)
                .placeholder(R.drawable.defaut_icon) // Imagem padrão exibida durante o carregamento
                .error(R.drawable.defaut_icon_rv) // Imagem padrão exibida se a URL da imagem for nula ou ocorrer algum erro no carregamento
                .into(binding.ivArticleImage)

            binding.tvTitle.text = article.author ?: article.source?.name
            binding.tvSorce.text = article.source?.name ?: article.author
            binding.tvDescription.text = article.description ?: "Description is empty"
            binding.tvPublishedAt.text = article.publishedAt?.let { UtilDateTime.formatDateTime(it) }

            itemView.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(article)
                }
            }


        }

    }


    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }



    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemFavoritosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }


    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.bind(article)

    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnclickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}