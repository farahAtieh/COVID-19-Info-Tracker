package com.example.covid_19infotracker.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.covid_19infotracker.data.model.News
import io.reactivex.rxjava3.core.Flowable

interface CountryNewsRepository {

    fun getCountryNews(countryCode: String, category: String): Flowable<PagingData<News.Article>>

}