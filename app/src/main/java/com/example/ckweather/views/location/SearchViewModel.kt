package com.example.ckweather.views.location

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Snackbar
import androidx.lifecycle.AndroidViewModel
import com.example.ckweather.data.api.GeocodingInterface
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.models.geocoding.GeocodingItem
import com.example.ckweather.util.OPENWEATHER_API_KEY
import com.example.ckweather.util.OPENWEATHER_API_ROOT
import com.example.ckweather.util.OPENWEATHER_LIMIT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

class SearchViewModel (app: Application): AndroidViewModel(app) {
//    private val repo = LocationRepository(app.applicationContext)
    private val context = app.applicationContext
    private var toastWasShow = false;
    private val _geocodingList = MutableStateFlow<List<GeocodingItem>>(emptyList())
    val geocodingList: StateFlow<List<GeocodingItem>> = _geocodingList

    fun fetchGeocoding(text: String){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OPENWEATHER_API_ROOT)
            .build()
            .create(GeocodingInterface::class.java)

        val retrofitItem = retrofitBuilder.getData(
            appid = OPENWEATHER_API_KEY,
            q = text,
            limit = OPENWEATHER_LIMIT
        )
//      make an asynchronous API request
        retrofitItem.enqueue(object: Callback<List<GeocodingItem>?> {
            override fun onResponse(
                call: Call<List<GeocodingItem>?>,
                response: Response<List<GeocodingItem>?>
            ) {
                try{
                    val responseBody = response.body()!!
                    val myStringBuilder = StringBuilder()

                    for(item in responseBody){
                        myStringBuilder.append(item.name)
                        myStringBuilder.append("\n")
                    }

                    _geocodingList.update { response.body()!! }
                } catch (e: Exception){
                    Log.e("Error", e.toString())
                }

            }

            override fun onFailure(call: Call<List<GeocodingItem>?>, t: Throwable) {
                if(!toastWasShow){
                    Toast.makeText(context,"Problem z internetem", Toast.LENGTH_SHORT).show()
                    toastWasShow = true;
                }
            }

        })
    }

    fun clearSearchList(){
        _geocodingList.update { emptyList() }
    }

    fun addNewLocation(
        geocodingItem: GeocodingItem,
        locationVm: LocationViewModel,
    ){
        val locationItem = WeatherItem(
            name = geocodingItem.name,
            lat = geocodingItem.lat,
            lon = geocodingItem.lon,
        )

        locationVm.insertWeather(locationItem)
        locationVm.updateWeather(locationItem)
        locationVm.updateForecast(locationItem)
        Toast.makeText(context, "Dodano nowe miasto", Toast.LENGTH_SHORT).show()
    }
}
