package com.example.covid_19infotracker.presentation.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.covid_19infotracker.data.model.News.Article
import com.example.covid_19infotracker.domain.usecase.GetCountryNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CountryNewsVM
    @Inject constructor(private val countryNewsUseCase: GetCountryNewsUseCase): ViewModel() {

    var countryArticle = MutableLiveData<PagingData<Article>>()

    private val category = "health"

    fun getNews(countryCode: String){

        countryNewsUseCase.getCountryNews(countryCode, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { articles ->
                    countryArticle.value = articles
                },
                { throwable ->
                    System.out.println() }
            )
    }

}