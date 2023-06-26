package com.example.ckweather.views.location

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.data.database.weather.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

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

    fun getWeather(): Flow<List<WeatherItem>> {

        val locationFlow = weatherRepository.getAll()
        runBlocking(Dispatchers.IO) {
            val locationList = locationFlow.first()
            Log.d("Location List:","List: $locationList")
            for(item in locationList){
                val deltaTime = item.dt?.toLong()?.times(1000)?.minus(System.currentTimeMillis())
                    ?.let { abs(it.toLong()) }
                Log.d("DeltaTime","$deltaTime")
//                if (deltaTime != null) {
//                    if( deltaTime > (1000 * 60 * 60)){
//                        Log.i("Update", "Location ID - ${item.uid}")
//                        updateLocationItem(item)
//                        updateLocationForecast(item)
//                    }
//                } else{
//                    updateLocationItem(item)
//                    updateLocationForecast(item)
//                }
            }

        }
        return locationFlow
    }
}