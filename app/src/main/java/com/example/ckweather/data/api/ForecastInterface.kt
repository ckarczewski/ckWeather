package com.example.ckweather.data.api

import com.example.ckweather.models.forecast.ForecastItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastInterface {
    @GET("data/2.5/forecast")
    fun getData(
        @Query("appid") appid: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<ForecastItem>
}