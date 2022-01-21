package com.example.covid_19infotracker

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            googleMap.setOnMapClickListener { position ->

                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1)

                if(addresses.size > 0) {
                    val country = addresses[0].countryName
                    Toast.makeText(this, country, Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}