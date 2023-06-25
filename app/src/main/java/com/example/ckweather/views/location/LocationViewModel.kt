package com.example.ckweather.views.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.data.database.weather.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
//TODO: change name from LocationViewModel on SearhLocationViewModel
class LocationViewModel (app: Application): AndroidViewModel(app) {
    private val weatherRepository = WeatherRepository(app.applicationContext)
    private val context = app.applicationContext
    private val _app = app

    fun insertWeather(locationItem: WeatherItem){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            weatherRepository.insertAll(listOf(locationItem))
        }
    }
}