package com.studycode.recyclerview.data.service.responses

data class CountriesItem(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Double,
    val country: String,
    val critical: Int,
    val deaths: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int
)