package com.example.covid_19infotracker.data.repository

import com.example.covid_19infotracker.data.api.GetCoronaTrackingDataApiEndPoint
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.domain.repository.CoronaTrackingDataRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class CoronaTrackingDataRepositoryImp @Inject constructor(private val getCoronaTrackingDataApiEndPoint: GetCoronaTrackingDataApiEndPoint): CoronaTrackingDataRepository {

    override fun getCoronaVirusDataByDateRange(
        url: String,
        dateForm: String,
        dateTo: String
    ): Flowable<CoronaTrackingData> {
        return getCoronaTrackingDataApiEndPoint.getCoronaVirusDataByDateRange(url, dateForm, dateTo)
    }
}