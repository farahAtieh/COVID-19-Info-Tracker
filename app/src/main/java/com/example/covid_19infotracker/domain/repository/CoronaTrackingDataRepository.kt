package com.example.covid_19infotracker.domain.repository

import com.example.covid_19infotracker.data.model.CoronaTrackingData
import io.reactivex.rxjava3.core.Flowable

interface CoronaTrackingDataRepository {

    fun getCoronaVirusDataByDateRange(url: String,
                                      dateForm: String,
                                      dateTo: String): Flowable<CoronaTrackingData>
}