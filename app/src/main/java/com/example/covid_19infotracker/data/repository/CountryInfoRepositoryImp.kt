package com.example.covid_19infotracker.data.repository

import com.example.covid_19infotracker.data.api.GetCountryInfoApiEndPoint
import com.example.covid_19infotracker.data.model.CountryInfo
import com.example.covid_19infotracker.domain.repository.CountryInfoRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class CountryInfoRepositoryImp @Inject constructor(private val getCountryInfoEndPoint: GetCountryInfoApiEndPoint): CountryInfoRepository {

    override fun getCountryInfo(url: String, fullText: Boolean): Flowable<List<CountryInfo>> {
        return getCountryInfoEndPoint.getCountryInfo(url, fullText)
    }
}