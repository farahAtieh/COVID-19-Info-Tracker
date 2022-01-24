package com.example.covid_19infotracker.domain.usecase

import com.example.covid_19infotracker.application.AppConstants
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.domain.repository.CoronaTrackingDataRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCoronaTrackingDataByDateRangeUseCase @Inject constructor(private val coronaTrackingDataRepository: CoronaTrackingDataRepository) {

    fun getCoronaVirusDataByDateRange(dateForm: String,
                                      dateTo: String): Flowable<CoronaTrackingData>{

        return coronaTrackingDataRepository.getCoronaVirusDataByDateRange(AppConstants.CORONA_TRACKING_DATA_BASE_URL, dateForm, dateTo)
    }
}