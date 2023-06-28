package com.example.ckweather.views.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ckweather.R
import com.example.ckweather.data.database.weather.WeatherItem

@Composable
fun WeatherWindow(
    weatherItem: WeatherItem
){
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 50.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(modifier = Modifier
                ,onClick = {
//                    navController.navigate("location")
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFFF5F5F5)
                )
            }
//            Text(
//                text = "Progrnoza Pogody",
//                color = Color(0xFFF5F5F5),
//                fontSize = 18.sp
//            )
            IconButton(
                modifier = Modifier
                ,onClick = {
//                    navController.navigate("setting")
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFFF5F5F5)
                )
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){

        Text(
            text = weatherItem.name,
            textAlign = TextAlign.Center,
            color = Color(0xFFFFFFFF),
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 26.dp, horizontal = 90.dp)

        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text ="${weatherItem.temp} K",
                    color = Color.White, fontSize = 55.sp,
                    textAlign = TextAlign.Center)
                Column() {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.clear_sky),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp).padding(end = 20.dp, top = 5.dp),
                        tint = Color(0xFFF5F5F5)
                    )
                    Text(text ="PODPIS", color = Color.White, fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Temperatura odczuwalna", color = Color.White, fontSize = 20.sp)
                Text(text ="${weatherItem.feelsLike}", color = Color.White, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Ciśnienie", color = Color.White, fontSize = 20.sp)
                Text(text ="${weatherItem.pressure} hPa", color = Color.White, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Wilgotność", color = Color.White, fontSize = 20.sp)
                Text(text ="${weatherItem.humidity} %", color = Color.White, fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text ="Wiatr", color = Color.White, fontSize = 20.sp)
                Text(text ="${weatherItem.windSpeed} m/s", color = Color.White, fontSize = 20.sp)
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