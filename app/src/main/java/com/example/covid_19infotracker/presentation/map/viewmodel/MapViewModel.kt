package com.example.covid_19infotracker.presentation.map.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid_19infotracker.domain.usecase.GetCountryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MapViewModel@Inject constructor(
    private val getCountryInfoUseCase: GetCountryInfoUseCase) : ViewModel() {

    var countryCode = MutableLiveData<String?>()

    fun getCountryInfo(countryName: String){

        getCountryInfoUseCase.getCountryInfo(countryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { countryInfo ->
                    countryCode.postValue(countryInfo[0].cca2)
                },
                { throwable ->
                    System.out.println() }
            )

    }

    override fun onCleared() {
        super.onCleared()
        countryCode.value = null
    }
}