package com.example.covid_19infotracker.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoronaTrackingData(
    val dates: Map<String, Countries>,
    val total: Total,
):Parcelable {

    @Parcelize
    data class Countries(
        val countries: Map<String, TrackedInfo>
    ):Parcelable

    @Parcelize
    data class Total(
        val today_vs_yesterday_confirmed: Double,
        val today_vs_yesterday_deaths: Double,
        val today_vs_yesterday_open_cases: Double,
        val today_vs_yesterday_recovered: Double,
    ): TrackedInfo(), Parcelable

    @Parcelize
    open class TrackedInfo(
        val today_confirmed: Int = 0,
        val today_deaths: Int = 0,
        val today_new_confirmed: Int = 0,
        val today_new_deaths: Int = 0,
        val today_new_open_cases: Int = 0,
        val today_new_recovered: Int = 0,
        val today_open_cases: Int = 0,
        val today_recovered: Int = 0,
        val yesterday_confirmed: Int = 0,
        val yesterday_deaths: Int = 0,
        val yesterday_open_cases: Int = 0,
        val yesterday_recovered: Int = 0
    ):Parcelable
}