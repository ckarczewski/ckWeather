package com.example.ckweather.views.location.favouriteLocation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ckweather.R
import com.example.ckweather.data.database.weather.WeatherItem
import com.example.ckweather.models.geocoding.GeocodingItem
import com.example.ckweather.views.location.LocationViewModel


@Composable
fun FavouriteLocationScreen(
    navController: NavHostController
) {
    val locationViewModel: LocationViewModel = viewModel()
    val weathersList = locationViewModel.getWeather().collectAsState(initial = emptyList())
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier.offset(x = 10.dp, y = 10.dp)
            ,onClick = {
                navController.navigate("home")
            }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_circle_left_24),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp),
                tint = Color(0xFF1b1e23)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Ulubione miasta",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        vertical = 20.dp
                    )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(horizontal = 15.dp)){
                FavouritesLazyColumn(locationsList = weathersList.value, deleteEvent = {weather -> locationViewModel.deleteWeather(weather)})
            }
        }

    }
}

@Composable
fun FavouritesLazyColumn(
    locationsList: List<WeatherItem>,
    deleteEvent: ((WeatherItem)->Unit)?=null
){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(items = locationsList, key = {it.uid}) {
            LocationRow(it, deleteEvent)
        }
    }
}


@Composable
fun LocationRow(
    locationsItem: WeatherItem,
    deleteEvent: ((WeatherItem) -> Unit)? = null
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(65.dp)){
        Row(modifier = Modifier.fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 10.dp)
            .background(color = Color(0xFF033761), shape = RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            if (locationsItem.name !=null){
                Text(text = locationsItem.name, modifier = Modifier.padding(horizontal = 25.dp, vertical = 5.dp), fontSize = 25.sp, color = Color.White)
            }

            val deleteIcon = ImageVector.vectorResource(id = R.drawable.delete_icon)
            IconButton(onClick = {
                if (locationsItem != null) {
                    deleteEvent?.invoke(locationsItem)
                }
            }) {
                Icon(
                    imageVector = deleteIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp).padding(horizontal = 4.dp),
                    tint = Color(0xFFDAD6D6)
                )
            }
        }
    }
}