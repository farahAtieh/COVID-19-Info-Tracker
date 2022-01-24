package com.example.covid_19infotracker.data.api

import com.example.covid_19infotracker.data.model.CountryInfo
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GetCountryInfoApiEndPoint {

    @GET
    fun getCountryInfo(
        @Url url: String,
        @Query("fullText") fullText: Boolean
    ): Flowable<List<CountryInfo>>
}