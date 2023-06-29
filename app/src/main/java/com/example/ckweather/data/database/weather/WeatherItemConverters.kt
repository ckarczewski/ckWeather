package com.example.ckweather.data.database.weather

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//TODO: Do wyjebania
class WeatherItemConverters {
    @TypeConverter
    fun fromWeatherItemToString(value: WeatherItem): String{
        val gson = Gson()
        val type = object : TypeToken<WeatherItem>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToWeatherItem(value: String): WeatherItem{
        val gson = Gson()
        val type = object : TypeToken<WeatherItem>(){}.type
        return gson.fromJson(value, type)
    }
}