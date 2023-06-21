package com.example.ckweather.views.setting

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ckweather.R


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SettingScreen (
    navController: NavHostController
) {
    Log.d("setting screen","dupa2")
    IconButton(
        modifier = Modifier
        ,onClick = {
            navController.navigate("home")
        }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_circle_left_24),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp),
            tint = Color(0xFFF5F5F5)
        )
    }
}