package com.example.ckweather.views.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.example.ckweather.R
import com.example.ckweather.data.database.weather.WeatherItem

import com.example.ckweather.views.location.LocationViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    navController: NavHostController
){
    Log.d("Screen","HomeScreen")
    PagerView()
    BottomMenu(navController = navController)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerView(){
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    PagerContent(pagerState = pagerState)
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(modifier = Modifier
                ,onClick = {
                    scope.launch {
                        if (pagerState.currentPage !=0) {
                            pagerState.animateScrollToPage(pagerState.currentPage-1)
                        }

                    }

                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFF1b1e23)
                )
            }

            IconButton(
                modifier = Modifier
                ,onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pagerState.pageCount-1){
                            pagerState.animateScrollToPage(pagerState.currentPage+1)
                        }
                    }
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFF1b1e23)
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 100.dp))

//        }
    }
}
@OptIn(ExperimentalPagerApi::class,ExperimentalFoundationApi::class)
@Composable
fun PagerContent(pagerState: PagerState){
    val locationViewModel: LocationViewModel = viewModel()
    val weatherData = locationViewModel.getWeather().collectAsState(initial = emptyList())
    val weathersList: MutableList<WeatherItem> = mutableListOf()

    if(!locationViewModel.isOnline()){
        Spacer(modifier = Modifier.padding(top = 200.dp))
        NoInternetConnection()
    }

    if(weatherData.value.isNotEmpty()){
        weathersList += weatherData.value
    }
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        HorizontalPager(count = weathersList.size, state = pagerState, modifier = Modifier.fillMaxSize()) {
            page-> if (weathersList[page].temp !=null){
                WeatherWindow(weatherItem = weathersList[page])
        }
        }
    }
}
@Composable
fun NoInternetConnection(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Box(modifier = Modifier.padding(bottom = 40.dp)){
            Text(
                text = "Brak dostępu do internetu, dane nie są aktualne",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(40.dp)
            )
        }
    }
}
@Composable
fun BottomMenu(
    navController: NavHostController
){
    Box(modifier = Modifier.fillMaxSize()) {
        BottomNavigation(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            backgroundColor = Color(0xFF3F3F3F)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 5.dp,
                        horizontal = 50.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    modifier = Modifier
                    ,onClick = {
                        navController.navigate("locations")
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Color(0xFFF5F5F5)
                    )
                }
                IconButton(
                    modifier = Modifier
                    ,onClick = {
                        navController.navigate("settings")
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.setting),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Color(0xFFF5F5F5)
                    )
                }
                IconButton(
                    modifier = Modifier
                    ,onClick = {
                        navController.navigate("favourite")
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_list_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = Color(0xFFF5F5F5)
                    )
                }
            }
        }

    }
}