package com.example.covid_19infotracker.presentation.map.view

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covid_19infotracker.R
import com.example.covid_19infotracker.data.model.CoronaTrackingData
import com.example.covid_19infotracker.data.model.CovidInfoPerCountry
import com.example.covid_19infotracker.databinding.FragmentMapBinding
import com.example.covid_19infotracker.presentation.map.viewmodel.MapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MapFragment: Fragment(R.layout.fragment_map), OnMapReadyCallback {


    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    lateinit var mBinding: FragmentMapBinding

    private val mViewModel: MapViewModel by activityViewModels()
    private val mArgs: MapFragmentArgs by navArgs()
    lateinit var coronaTrackingData: CoronaTrackingData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        mapFragment = childFragmentManager.findFragmentById(R.id.mapContainer) as SupportMapFragment

        coronaTrackingData = mArgs.coronaTrackingInfo

        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment.getMapAsync(this)

        mBinding.ivTotalInfo.setOnClickListener{
            showCovidRelatedInfoDialog(null)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        googleMap.setOnMapClickListener { position ->

            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1)

            if(addresses.size > 0) {
                val country = addresses[0].countryName
                showCovidRelatedInfoDialog(country)
                mViewModel.getCountryInfo(country)
            }
        }
    }

    private fun showCovidRelatedInfoDialog(country: String?) {
        if(country == null){

            val covidInfoPerCountry = CovidInfoPerCountry(
                country,
                coronaTrackingData.total
            )

            showDialog(covidInfoPerCountry)
        }else{
            val key = coronaTrackingData.dates.keys.first()
            val covidInfoPerCountry = CovidInfoPerCountry(
                country,
                coronaTrackingData.dates[key]?.countries?.get(country)
            )
            showDialog(covidInfoPerCountry)

        }


    }

    private fun showDialog(covidInfoPerCountry: CovidInfoPerCountry) {
        val action = MapFragmentDirections.actionMapFragmentToCovidInfoDialog(covidInfoPerCountry)
        findNavController().navigate(action)
    }

}