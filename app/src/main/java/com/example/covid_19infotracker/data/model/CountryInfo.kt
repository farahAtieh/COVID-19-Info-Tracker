package com.example.covid_19infotracker.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo(
    val name: Name,
    val cca2: String
): Parcelable {

    @Parcelize
    data class Name(
            val common: String,
            val official: String,
            val nativeName: NativeName
        ): Parcelable {

        @Parcelize
        data class NativeName(
            val ara: Ara
        ): Parcelable{

            @Parcelize
            data class Ara(val official: String,
                           val common: String): Parcelable
        }
    }
}



