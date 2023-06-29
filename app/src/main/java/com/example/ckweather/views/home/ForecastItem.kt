package com.example.ckweather.views.home


import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ForecastItem(date: String, icon: ImageVector, temperature: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = temperature,
            color = Color.Black
        )
        Text(
            text = date,
            color = Color.Black
        )
    }
}
