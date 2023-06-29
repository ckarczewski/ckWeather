package com.example.ckweather.views.home

//import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.background
import java.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ckweather.R
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.helpers.helpers
import kotlin.math.roundToInt

@Composable
fun WeatherWindow(
    weatherItem: WeatherItem
){
    Text(
        text = weatherItem.name,
        textAlign = TextAlign.Center,
        color = Color(0xFF000000),
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 26.dp, horizontal = 90.dp)

    )
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){


        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
//               TEMPERATURA
                val temp = helpers.temperatureCalculation(weatherItem.temp!!).roundToInt().toString()
                val unit = helpers.getTemperatureUnit()
                Log.d("temp w weatherwindow","$unit")
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text ="$temp $unit",
                    color = Color.Black, fontSize = 55.sp,
                    textAlign = TextAlign.Center)
                Column() {
//               IKONA I OPIS
                    weatherItem.weatherID?.let {
                        helpers.getWeatherIcon(
                            it
                        )
                    }?.let { ImageVector.vectorResource(id = it) }?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(end = 20.dp, top = 5.dp),
                            tint = Color(0xFF1b1e23)
                        )
                    }
                    Text(text = weatherItem.weatherID?.let { helpers.getWeatherDescription(it) }.toString(), color = Color.Black, fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                val temp = helpers.temperatureCalculation(weatherItem.feelsLike!!).roundToInt().toString()
                val unit = helpers.getTemperatureUnit()
                Text(text ="Temperatura odczuwalna", color = Color.Black, fontSize = 20.sp)
                Text(text ="$temp $unit", color = Color.Black, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Ciśnienie", color = Color.Black, fontSize = 20.sp)
                Text(text ="${weatherItem.pressure} hPa", color = Color.Black, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Wilgotność", color = Color.Black, fontSize = 20.sp)
                Text(text ="${weatherItem.humidity} %", color = Color.Black, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Wiatr", color = Color.Black, fontSize = 20.sp)
                Text(text ="${weatherItem.windSpeed} m/s", color = Color.Black, fontSize = 20.sp)
            }

            Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if(weatherItem.forecasts.isNotEmpty()){
                        val dateFormat = SimpleDateFormat("dd-MM")
                        repeat(5){ item ->
                            val icon = ImageVector.vectorResource(id = helpers.getWeatherIcon(weatherItem.forecasts[item].weatherID))
                            val temp = helpers.temperatureCalculation(weatherItem.forecasts[item].temp).roundToInt().toString()
                            val unit = helpers.getTemperatureUnit()
                            ForecastItem(
                                date = dateFormat.format(weatherItem.forecasts[item].time),
                                icon = icon,
                                temperature = temp + "" + unit
                            )
                        }
                    }
                }
            }

        }
    }

}

@Composable
@Preview
fun WeatherPagePreview(){

    WeatherWindow(
        WeatherItem(
            uid = 0,
            name = "Waszyngton",
            lat = 55.1,
            lon = 23.1,
            temp = 280.0,
            feelsLike = 285.0,
            pressure = 1012.0 ,
            humidity = 70.0,
            windSpeed = 12.2,
            dt = 123.0,
        ),
    )
}