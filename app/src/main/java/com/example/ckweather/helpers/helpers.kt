package com.example.ckweather.helpers

import com.example.ckweather.R

object helpers {
    fun getWeatherIcon(id: Int): Int {
        val idStr = id.toString()[0].toString()
        when(idStr){
            "2" -> {
                return R.drawable.thunderstorm_48px
            }
            "3" -> {
                return R.drawable.rainy_48px
            }
            "5" -> {
                return R.drawable.rainy_48px
            }
            "6" -> {
                return R.drawable.cloudy_snowing_48px
            }
            "7" -> {
                return R.drawable.cloudy_48px
            }
            "8" -> {
                if (id==800){
                    return R.drawable.sunny_48px
                } else{
                    return R.drawable.partly_cloudy_day_48px
                }

            }
            else ->{
                return R.drawable.cloudy_48px
            }
        }
    }
}