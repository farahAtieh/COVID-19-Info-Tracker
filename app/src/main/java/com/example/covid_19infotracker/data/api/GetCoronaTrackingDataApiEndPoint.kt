package com.example.covid_19infotracker.data.api

import com.example.covid_19infotracker.data.model.CoronaTrackingData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GetCoronaTrackingDataApiEndPoint {

    @GET
    fun getCoronaVirusDataByDateRange(@Url url: String,
                                      @Query("date_from") dateForm: String,
                                      @Query("date_to") dateTo: String): Flowable<CoronaTrackingData>
}