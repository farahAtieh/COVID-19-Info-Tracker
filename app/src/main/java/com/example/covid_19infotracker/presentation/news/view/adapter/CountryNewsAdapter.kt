package com.example.covid_19infotracker.presentation.news.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.News
import com.example.covid_19infotracker.databinding.ItemCountryArticleBinding

class CountryNewsAdapter(private val listener: OnItemClickListener): PagingDataAdapter<News.Article, CountryNewsAdapter.ArticleViewHolder>(ARTICLE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemCountryArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }


    inner class ArticleViewHolder(private val binding: ItemCountryArticleBinding)
        : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null){
                        listener.onItemClick(item)
                    }
                }
            }
        }
            fun bind(currentArticle: News.Article){
                binding.apply {

                    artical = currentArticle

                    Glide.with(itemView)
                        .load(currentArticle.urlToImage)
                        .centerCrop()
                        .thumbnail(0.1f)
                        .error(R.drawable.ic_placeholder_article)
                        .into(ivArticleImg)

                }
            }
        }

    interface OnItemClickListener{
        fun onItemClick(article: News.Article)
    }

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<News.Article>(){
            override fun areItemsTheSame(oldItem: News.Article, newItem: News.Article) =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: News.Article, newItem: News.Article) =
                oldItem == newItem

        }
    }


}