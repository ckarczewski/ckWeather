package com.example.ckweather.data.database.weather

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ForecastConverters {
    @TypeConverter
    fun fromListForecastToString(value: List<Forecast>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Forecast>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToListForecast(value: String): List<Forecast> {
        val gson = Gson()
        val type = object : TypeToken<List<Forecast>>() {}.type
        return gson.fromJson(value, type)
    }
}