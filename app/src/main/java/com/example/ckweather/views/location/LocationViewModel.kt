package com.example.ckweather.views.location

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ckweather.data.api.ForecastInterface
import com.example.ckweather.data.api.WeatherInterface
import com.example.ckweather.data.database.weather.Forecast
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.data.database.weather.WeatherRepository
import com.example.ckweather.helpers.helpers
import com.example.ckweather.models.forecast.ForecastItem
import com.example.ckweather.models.weather.Weather
import com.example.ckweather.util.OPENWEATHER_API_KEY
import com.example.ckweather.util.OPENWEATHER_API_ROOT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

//TODO: change name from LocationViewModel on SearhLocationViewModel
class LocationViewModel (app: Application): AndroidViewModel(app) {
    private val weatherRepository = WeatherRepository(app.applicationContext)
    @SuppressLint("StaticFieldLeak")
    private val context = app.applicationContext
    private val _app = app

    fun insertWeather(weatherItem: WeatherItem){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            weatherRepository.insertAll(listOf(weatherItem))
        }
    }

    fun deleteWeather(weatherItem: WeatherItem) = CoroutineScope(viewModelScope.coroutineContext).launch{
        weatherRepository.delete(listOf(weatherItem))
    }

    fun updateWeather(weatherItem: WeatherItem){
        Log.d("update weather", "{$weatherItem}")
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OPENWEATHER_API_ROOT)
            .build()
            .create(WeatherInterface::class.java)
        val retrofitItems = weatherItem.lon?.let {lon->
            weatherItem.lat?.let { lat ->
                retrofitBuilder.getData(
                    appid = OPENWEATHER_API_KEY,
                    lat = lat,
                    lon = lon
                )
            }
        }

        retrofitItems?.enqueue(object: Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                try {
                    weatherItem.temp = response.body()!!.main.temp
                    weatherItem.pressure = response.body()!!.main.pressure.toDouble()
                    weatherItem.humidity = response.body()!!.main.humidity.toDouble()
                    weatherItem.windSpeed = response.body()!!.wind.speed
                    weatherItem.feelsLike = response.body()!!.main.feels_like
                    weatherItem.dt = response.body()!!.dt.toDouble()
                    weatherItem.weatherID = response.body()!!.weather[0].id
                    CoroutineScope(viewModelScope.coroutineContext).launch { weatherRepository.update(weatherItem) }

                } catch (e:Exception){
                    Log.e("Update weather error: ", e.toString())
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.e("Update weather error: ", "Callback fail")
            }
        })

    }

    fun updateForecast(weatherItem: WeatherItem){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OPENWEATHER_API_ROOT)
            .build()
            .create(ForecastInterface::class.java)
        val retrofitItems = weatherItem.lon?.let {lon->
            weatherItem.lat?.let { lat ->
                retrofitBuilder.getData(
                    appid = OPENWEATHER_API_KEY,
                    lat = lat,
                    lon = lon
                )
            }
        }
        retrofitItems?.enqueue(object: Callback<ForecastItem>{
            override fun onResponse(call: Call<ForecastItem>, response: Response<ForecastItem>) {
                try {
                    val forecastItemList: MutableList<Forecast> = emptyList<Forecast>().toMutableList()
                    for (i in 0..4){
                        forecastItemList += Forecast(
                            response.body()!!.list[i*8].dt.toLong()*1000,
                            response.body()!!.list[i*8].main.temp,
                            response.body()!!.list[i*8].weather[0].id
                        )
                    }
                    weatherItem.forecasts = forecastItemList
                    CoroutineScope(viewModelScope.coroutineContext).launch {
                        weatherRepository.update(weatherItem)
                    }
                } catch (e: Exception){
                    Log.e("Forecast update error: ", e.toString())
                }
            }
            override fun onFailure(call: Call<ForecastItem>, t: Throwable) {

            }

        })

    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
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
//                ----
                if (deltaTime != null) {
                    if( deltaTime > (1000 * 60 * 60)){
                        Log.i("Update", "Location ID - ${item.uid}")
                        updateWeather(item)
                        updateForecast(item)
                    }
                } else{
                    updateWeather(item)
                    updateForecast(item)
                }
//                ----
            }

        }
        return locationFlow
    }

    fun updateAllWeatherDb() {
        val locationFlow = weatherRepository.getAll()
        runBlocking(Dispatchers.IO) {
            val locationList = locationFlow.first()
            for(item in locationList){
                Log.i("sss", item.toString())
                updateWeather(item)
                updateForecast(item)
            }

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context,"Zaktualizowano dane pogodowe", Toast.LENGTH_SHORT).show()
            }
        }
    }
}