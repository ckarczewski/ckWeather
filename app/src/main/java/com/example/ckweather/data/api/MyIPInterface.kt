package com.example.ckweather.data.api

import com.example.ckweather.models.myip.MyIp
import retrofit2.Call
import retrofit2.http.GET


interface MyIPInterface {
    @GET("/")
    fun getData(
    ): Call<MyIp>
}