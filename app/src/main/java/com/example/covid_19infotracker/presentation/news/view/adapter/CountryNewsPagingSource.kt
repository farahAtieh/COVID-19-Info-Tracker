package com.example.covid_19infotracker.presentation.news.view.adapter

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.covid_19infotracker.application.AppConstants
import com.example.covid_19infotracker.data.api.GetCountryNewsApiEndPoint
import com.example.covid_19infotracker.data.model.News
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private const val COUNTRY_NEWS_STARTED_PAGE_INDEX = 1

class CountryNewsPagingSource(
    private val getCountryNewsApiEndPoint: GetCountryNewsApiEndPoint,
    private val countryCode: String,
    private val category: String,
    ): RxPagingSource<Int, News.Article>() {

    override fun getRefreshKey(state: PagingState<Int, News.Article>): Int? {
        TODO("Not yet implemented")
    }

    // trigger api request and turn the data into pages
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, News.Article>> {
        val position = params.key ?: COUNTRY_NEWS_STARTED_PAGE_INDEX

        return getCountryNewsApiEndPoint.getCountryNews(
            AppConstants.NEWS_BASE_URL,
            countryCode,
            category,
            AppConstants.NEWS_API_KEY,
            position,
            params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResults(it, position)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }

    }

    private fun toLoadResults(news: News, position: Int): LoadResult<Int, News.Article> {
        return LoadResult.Page(
            data = news.articles,
            prevKey = if(position == COUNTRY_NEWS_STARTED_PAGE_INDEX) null else position - 1,
            nextKey = if(news.articles.isEmpty()) null else position + 1
        )
    }
}