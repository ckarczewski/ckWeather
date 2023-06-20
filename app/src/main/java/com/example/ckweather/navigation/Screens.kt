package com.example.ckweather.navigation

sealed class Screens(val route: String) {
    object Locations: Screens("locations")
    object Home: Screens("home")
    object Settings: Screens("settings")

}