package com.example.covid_19infotracker.domain.usecase

import androidx.paging.PagingData
import com.example.covid_19infotracker.data.model.News
import com.example.covid_19infotracker.domain.repository.CountryNewsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ViewModelScoped
class GetCountryNewsUseCase
    @Inject constructor(private val countryNewsRepository: CountryNewsRepository) {

    fun getCountryNews(
                countryCode: String,
                category: String): Flowable<PagingData<News.Article>>{
        return countryNewsRepository.getCountryNews(countryCode, category)
    }
}