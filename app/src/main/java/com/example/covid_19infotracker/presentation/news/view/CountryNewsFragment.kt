package com.example.covid_19infotracker.presentation.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.databinding.FragmentCountryNewsBinding
import com.example.covid_19infotracker.presentation.news.view.adapter.CountryNewsAdapter
import com.example.covid_19infotracker.presentation.news.viewmodel.CountryNewsVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryNewsFragment: Fragment(R.layout.fragment_country_news) {

    private lateinit var countryCode: String
    private lateinit var mBinding: FragmentCountryNewsBinding
    private val mArgs: CountryNewsFragmentArgs by navArgs()
    val countryNewsVM: CountryNewsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_news, container, false)

        countryCode = mArgs.countryCode

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CountryNewsAdapter()

        mBinding.apply {
            rvCountryArticles.setHasFixedSize(true)
            rvCountryArticles.adapter = adapter
        }
        countryNewsVM.getNews(countryCode)

        countryNewsVM.countryArticle.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}