package com.example.ckweather.navigation
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.ckweather.views.home.HomeScreen
import com.example.ckweather.views.location.LocationScreen
import com.example.ckweather.views.setting.SettingScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screens.Home.route
    ){
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Locations.route){
            LocationScreen(navController)
        }
        composable(route = Screens.Settings.route){
            SettingScreen(navController)
        }

    }
}