package com.example.ckweather.views.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ckweather.R

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    navController: NavHostController
){
    Log.d("dupa","dupa2")
    Box(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "Progrnoza Pogody",
            color = Color.Black,
            fontSize = 18.sp
        )
    }

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
                    imageVector = ImageVector.vectorResource(id = R.drawable.add_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFF000000)
                )
            }
            Text(
                text = "Progrnoza Pogody",
                color = Color.Black,
                fontSize = 18.sp
            )
            IconButton(
                modifier = Modifier
                ,onClick = {
                    navController.navigate("setting")
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFFF5F5F5)
                )
            }
        }
    }
}