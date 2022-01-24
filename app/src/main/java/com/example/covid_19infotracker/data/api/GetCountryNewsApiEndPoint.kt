package com.example.covid_19infotracker.data.api

import com.example.covid_19infotracker.data.model.News
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GetCountryNewsApiEndPoint {

    @GET
    fun getCountryNews(
        @Url url : String,
        @Query("country") countryCode: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
    ): Single<News>

}