package com.example.ckweather.helpers

import com.example.ckweather.R

object helpers {
    enum class TempUnits {C,F,K}

    fun getWeatherIcon(id: Int): Int {
        val idStr = id.toString()[0].toString()
        when (idStr) {
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
                if (id == 800) {
                    return R.drawable.sunny_48px
                } else {
                    return R.drawable.partly_cloudy_day_48px
                }

            }
            else -> {
                return R.drawable.cloudy_48px
            }
        }
    }

    fun getWeatherDescription(id: Int): String {
        val idStr = id.toString()[0].toString()
        when (idStr) {
            "2" -> {
                return "Burza"
            }
            "3" -> {
                return "Deszczyk"
            }
            "5" -> {
                return "Deszcz"
            }
            "6" -> {
                return "Śnieg"
            }
            "7" -> {
                return "Zachmurzenie"
            }
            "8" -> {
                if (id == 800) {
                    return "Słonecznie"
                } else {
                    return "Częściowe zachmurzenie"
                }

            }
            else -> {
                return "Chmury"
            }
        }
    }

    fun temperatureCalculation(temp: Double){

    }

    fun getTemperatureUnit(){

    }

}