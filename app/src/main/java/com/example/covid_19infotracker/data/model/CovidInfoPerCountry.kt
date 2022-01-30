package com.example.covid_19infotracker.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CovidInfoPerCountry(
    val countryName: String?,
    val covidRelatedInfo: CoronaTrackingData.TrackedInfo?,
):Parcelable