package com.example.covid_19infotracker.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoronaTrackingData(
    val dates: String,
    val metadata: TrackingMetaData,
    val total: TrackingTotalData
): Parcelable {

    @Parcelize
    data class TrackingMetaData(
        val by: String,
        val url: List<String>
    ): Parcelable

    @Parcelize
    data class TrackingTotalData(
        val date: String,
        val name: String,
        val name_es: String,
        val name_it: String,
        val rid: String,
        val source: String,
        val today_confirmed: Int,
        val today_deaths: Int,
        val today_new_confirmed: Int,
        val today_new_deaths: Int,
        val today_new_open_cases: Int,
        val today_new_recovered: Int,
        val today_open_cases: Int,
        val today_recovered: Int,
        val today_vs_yesterday_confirmed: Double,
        val today_vs_yesterday_deaths: Double,
        val today_vs_yesterday_open_cases: Double,
        val today_vs_yesterday_recovered: Double,
        val yesterday_confirmed: Int,
        val yesterday_deaths: Int,
        val yesterday_open_cases: Int,
        val yesterday_recovered: Int
    ): Parcelable
}