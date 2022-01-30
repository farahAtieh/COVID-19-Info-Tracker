package com.example.covid_19infotracker.presentation.common

import java.text.SimpleDateFormat
import java.util.*

fun getDate(): String{
    val df = SimpleDateFormat("yyyy-MM-dd")
    val formatted: String = df.format(Date())

    return formatted
}