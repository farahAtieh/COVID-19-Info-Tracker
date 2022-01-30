package com.example.covid_19infotracker.presentation.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.domain.usecase.GetCoronaTrackingDataByDateRangeUseCase
import com.example.covid_19infotracker.presentation.common.SingleLiveEvent
import com.example.covid_19infotracker.presentation.common.getDate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val getCoronaTrackingDataByDateRangeUseCase: GetCoronaTrackingDataByDateRangeUseCase)
    : ViewModel() {

    var coronaTrackingData = SingleLiveEvent<CoronaTrackingData>()

    init {
        getCoronaTrackingDataByDateRange()
    }

    private fun getCoronaTrackingDataByDateRange(){

        getCoronaTrackingDataByDateRangeUseCase.getCoronaVirusDataByDateRange(getDate(), getDate())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {dataByDateRange ->
                    coronaTrackingData.value = dataByDateRange
                },
                {throwable ->
                    System.out.println()
                }
            )
    }

}