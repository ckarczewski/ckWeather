package com.example.ckweather.data.database.weather

import androidx.room.Entity
import androidx.room.TypeConverters
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
@TypeConverters(ForecastConverters::class)
data class WeatherItem(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    var name: String = "",
    var lat: Double? = null,
    var lon: Double? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var pressure: Double? = null,
    var humidity: Double? = null,
    var windSpeed: Double? = null,
    var dt: Double? = null,
    var forecasts: List<Forecast> = emptyList<Forecast>(),
    var weatherID: Int? = null
)