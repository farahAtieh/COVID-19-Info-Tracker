package com.example.covid_19infotracker.application.di

import com.example.covid_19infotracker.data.api.GetCoronaTrackingDataApiEndPoint
import com.example.covid_19infotracker.data.api.GetCountryInfoApiEndPoint
import com.example.covid_19infotracker.data.api.GetCountryNewsApiEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EndPointModule {

    @Singleton
    @Provides
    fun provideCountryNewsEndPoint(retrofit: Retrofit): GetCountryNewsApiEndPoint =
        retrofit.create(GetCountryNewsApiEndPoint::class.java)

    @Singleton
    @Provides
    fun provideCountryInfoEndPoint(retrofit: Retrofit): GetCountryInfoApiEndPoint =
        retrofit.create(GetCountryInfoApiEndPoint::class.java)

    @Singleton
    @Provides
    fun provideCoronaTrackingDataEndPoint(retrofit: Retrofit): GetCoronaTrackingDataApiEndPoint =
        retrofit.create(GetCoronaTrackingDataApiEndPoint::class.java)
}