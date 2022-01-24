package com.example.covid_19infotracker.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.covid_19infotracker.data.api.GetCountryNewsApiEndPoint
import com.example.covid_19infotracker.data.model.News
import com.example.covid_19infotracker.domain.repository.CountryNewsRepository
import com.example.covid_19infotracker.presentation.news.view.adapter.CountryNewsPagingSource
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class CountryNewsRepositoryImp
    @Inject constructor(private val getCountryNewsApiEndPoint: GetCountryNewsApiEndPoint): CountryNewsRepository {

    override fun getCountryNews(
        countryCode: String,
        category: String
    ): Flowable<PagingData<News.Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CountryNewsPagingSource(getCountryNewsApiEndPoint, countryCode, category)}
        ).flowable
    }
}