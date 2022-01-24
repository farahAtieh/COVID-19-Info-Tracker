package com.example.covid_19infotracker.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.domain.usecase.GetCoronaTrackingDataByDateRangeUseCase
import com.example.covid_19infotracker.domain.usecase.GetCountryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoronaTrackingDataByDateRangeUseCase: GetCoronaTrackingDataByDateRangeUseCase)
    : ViewModel() {

    var coronaTrackingData = MutableLiveData<CoronaTrackingData.TrackingTotalData>()

    fun getCoronaTrackingDataByDateRange(){

        getCoronaTrackingDataByDateRangeUseCase.getCoronaVirusDataByDateRange("2020-03-10", "2020-03-10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {dataByDateRange ->
                    coronaTrackingData.value = dataByDateRange.total
                },
                {throwable ->
                    System.out.println()
                }
            )
    }
}