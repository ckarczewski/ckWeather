package com.example.ckweather.models.forecast

data class ForecastItem(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Data>,
    val message: Int
)
