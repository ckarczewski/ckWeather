package com.example.ckweather.data.api

import com.example.ckweather.models.iptolocation.IpToLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IpToLocationInterface {
    @GET("{ip}")
    fun getData(
        @Path("ip") ip: String,
        @Query("apikey") apikey: String
    ): Call<IpToLocation>
}