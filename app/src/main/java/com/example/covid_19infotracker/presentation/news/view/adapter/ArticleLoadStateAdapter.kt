package com.example.covid_19infotracker.presentation.news.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19infotracker.databinding.ArticleLoadStateFooterBinding

class ArticleLoadStateAdapter(private val retry: () -> Unit)
    : LoadStateAdapter<ArticleLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ArticleLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(val binding: ArticleLoadStateFooterBinding):
        RecyclerView.ViewHolder(binding.root){

        init {

            binding.btnRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading

            }
        }
    }

}