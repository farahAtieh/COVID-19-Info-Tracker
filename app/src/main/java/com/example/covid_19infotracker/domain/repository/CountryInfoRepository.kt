package com.example.covid_19infotracker.domain.repository

import com.example.covid_19infotracker.data.model.CountryInfo
import io.reactivex.rxjava3.core.Flowable

interface CountryInfoRepository {

    fun getCountryInfo(url: String, fullText: Boolean): Flowable<List<CountryInfo>>
}