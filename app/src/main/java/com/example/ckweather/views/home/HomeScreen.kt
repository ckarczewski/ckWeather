package com.example.ckweather.views.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ckweather.R

//@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    navController: NavHostController
){
    Log.d("dupa","dupa2")


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
                    navController.navigate("location")
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFFF5F5F5)
                )
            }
            Text(
                text = "Progrnoza Pogody",
                color = Color(0xFFF5F5F5),
                fontSize = 18.sp
            )
            IconButton(
                modifier = Modifier
                ,onClick = {
                    navController.navigate("setting")
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
//    WeatherWindow(weatherItem = )
    BottomMenu(navController = navController)
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