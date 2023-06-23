package com.example.ckweather.data.api

import com.example.ckweather.models.geocoding.GeocodingItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingInterface {
    @GET("geo/1.0/direct")
    fun getData(
        @Query("appid") appid: String,
        @Query("q") q: String,
        @Query("limit") limit: String
    ): Call<List<GeocodingItem>>
}