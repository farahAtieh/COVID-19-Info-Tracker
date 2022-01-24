package com.example.covid_19infotracker.domain.usecase

import com.example.covid_19infotracker.application.AppConstants
import com.example.covid_19infotracker.data.model.CountryInfo
import com.example.covid_19infotracker.domain.repository.CountryInfoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCountryInfoUseCase @Inject constructor(private val countryInfoRepository: CountryInfoRepository) {

    private val fullText = true;

    fun getCountryInfo(countryName: String): Flowable<List<CountryInfo>>{

        return countryInfoRepository.getCountryInfo(AppConstants.COUNTRIES_INFO_BASE_URL + countryName, fullText)
    }
}