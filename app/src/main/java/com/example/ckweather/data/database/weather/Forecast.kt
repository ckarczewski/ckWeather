package com.example.ckweather.data.database.weather

data class Forecast(
    var time: Long,
    var temp: Double,
    var weatherID: Int
)