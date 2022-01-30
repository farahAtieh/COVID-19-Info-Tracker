package com.example.covid_19infotracker.presentation.splash.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.presentation.map.view.MapFragmentDirections
import com.example.covid_19infotracker.presentation.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val mViewModel: SplashViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.coronaTrackingData.observe(viewLifecycleOwner,{
            if (it != null){
                openMapFragment(it)
            }
        })
    }

    private fun openMapFragment(coronaTrackingData: CoronaTrackingData) {
        val action = SplashFragmentDirections.actionSplashFragmentToMapFragment(coronaTrackingData)
        findNavController().navigate(action)
    }

}