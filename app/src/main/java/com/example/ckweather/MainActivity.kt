package com.example.ckweather

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.ckweather.helpers.helpers
import com.example.ckweather.ui.theme.CkWeatherTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.ckweather.navigation.NavGraph


class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CkWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0x978ED1FC)
                ) {
                    val systemUiController = rememberSystemUiController()

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color(0xFF111111)
                        )
                    }
                    val navController = rememberNavController()
                    NavGraph(navController = navController)

                    val setting = mainViewModel.getSetting().collectAsState(initial = emptyList())
                    if (setting.value.isNotEmpty()){
                        helpers.setSettingItem(setting.value[0])
                    }
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CkWeatherTheme {
//        Greeting("Android")
//    }
//}