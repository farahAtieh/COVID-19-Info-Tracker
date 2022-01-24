package com.example.covid_19infotracker.application.di

import com.example.covid_19infotracker.data.api.GetCoronaTrackingDataApiEndPoint
import com.example.covid_19infotracker.data.api.GetCountryInfoApiEndPoint
import com.example.covid_19infotracker.data.api.GetCountryNewsApiEndPoint
import com.example.covid_19infotracker.data.repository.CoronaTrackingDataRepositoryImp
import com.example.covid_19infotracker.data.repository.CountryInfoRepositoryImp
import com.example.covid_19infotracker.data.repository.CountryNewsRepositoryImp
import com.example.covid_19infotracker.domain.repository.CoronaTrackingDataRepository
import com.example.covid_19infotracker.domain.repository.CountryInfoRepository
import com.example.covid_19infotracker.domain.repository.CountryNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCountryNewsRepository(getCountryNewsApiEndPoint: GetCountryNewsApiEndPoint): CountryNewsRepository =
        CountryNewsRepositoryImp(getCountryNewsApiEndPoint)

    @Singleton
    @Provides
    fun provideCountryInfoRepository(getCountryInfoApiEndPoint: GetCountryInfoApiEndPoint): CountryInfoRepository =
        CountryInfoRepositoryImp(getCountryInfoApiEndPoint)

    @Singleton
    @Provides
    fun provideCoronaTrackingDataRepository(getCoronaTrackingDataApiEndPoint: GetCoronaTrackingDataApiEndPoint): CoronaTrackingDataRepository =
        CoronaTrackingDataRepositoryImp(getCoronaTrackingDataApiEndPoint)
}