package com.example.covid_19infotracker.presentation.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.News
import com.example.covid_19infotracker.databinding.FragmentCountryNewsBinding
import com.example.covid_19infotracker.presentation.news.view.adapter.ArticleLoadStateAdapter
import com.example.covid_19infotracker.presentation.news.view.adapter.CountryNewsAdapter
import com.example.covid_19infotracker.presentation.news.viewmodel.CountryNewsVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryNewsFragment : Fragment(R.layout.fragment_country_news), CountryNewsAdapter.OnItemClickListener {

    private lateinit var countryCode: String
    private lateinit var mBinding: FragmentCountryNewsBinding
    private val mArgs: CountryNewsFragmentArgs by navArgs()
    val countryNewsVM: CountryNewsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_news, container, false)

        countryCode = mArgs.countryCode

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CountryNewsAdapter(this)

        mBinding.apply {
            rvCountryArticles.setHasFixedSize(true)
            rvCountryArticles.itemAnimator = null
            rvCountryArticles.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ArticleLoadStateAdapter { adapter.retry() },
                footer = ArticleLoadStateAdapter { adapter.retry() }
            )

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        countryNewsVM.getNews(countryCode)

        countryNewsVM.countryArticle.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            mBinding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvCountryArticles.isVisible = loadState.source.refresh is LoadState.NotLoading
                tvErrorInfo.isVisible = loadState.source.refresh is LoadState.Error
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error

                //empty view
                if(loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1){
                    rvCountryArticles.isVisible = false
                    tvEmpty.isVisible = true
                }else {
                    tvEmpty.isVisible = false
                }
            }
        }
    }

    override fun onItemClick(article: News.Article) {
        val action = CountryNewsFragmentDirections.actionCountryNewsFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }
}