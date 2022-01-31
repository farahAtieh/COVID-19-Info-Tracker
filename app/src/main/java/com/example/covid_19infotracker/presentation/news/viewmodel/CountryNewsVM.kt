package com.example.covid_19infotracker.presentation.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.covid_19infotracker.presentation.common.SingleLiveEvent
import com.example.covid_19infotracker.data.model.News.Article
import com.example.covid_19infotracker.domain.usecase.GetCountryNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CountryNewsVM
    @Inject constructor(private val countryNewsUseCase: GetCountryNewsUseCase): ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    var countryArticle = SingleLiveEvent<PagingData<Article>>()

    private val category = "health"

    fun getNews(countryCode: String){

        mCompositeDisposable.add(
            countryNewsUseCase.getCountryNews(countryCode, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { articles ->
                    countryArticle.value = articles
                },
                { throwable ->
                    System.out.println() }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}