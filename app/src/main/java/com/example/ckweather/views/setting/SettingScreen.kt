package com.example.ckweather.views.setting

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ckweather.MainViewModel
import com.example.ckweather.R
import com.example.ckweather.helpers.helpers
import com.example.ckweather.views.location.LocationViewModel


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SettingScreen (
    navController: NavHostController
) {
    Log.d("setting screen","dupa2")

    var tempUnit by remember { mutableStateOf(helpers.getTemperatureUnit()) }
    var mainViewModel: MainViewModel = viewModel();
    var locationViewModel: LocationViewModel = viewModel()
    var expand by remember { mutableStateOf(false) }
    val suggestions = helpers.TempUnits.values()
    Column() {
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
        Row() {
            Text(
                modifier = Modifier.padding(all = 15.dp),
                text = "Jednostka temperatury: ",
                color = Color.Black,
                fontSize = 18.sp
            )
            Box(){
                OutlinedButton(
                    border = BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(25.dp),
                    onClick = { expand = true },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {

                    Log.i("test", tempUnit)


                    when(tempUnit){
                        "°C" -> {
                            Text(
                                text = "°C",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }

                        "K" -> {
                            Text(
                                text = "K",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }

                        "°F" -> {
                            Text(
                                text = "°F",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }
                    }

                }

                DropdownMenu(
                    expanded = expand,
                    onDismissRequest = { expand = false }
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            mainViewModel.changeTemperatureUnit(label)
                            tempUnit = when(label){
                                helpers.TempUnits.C -> "°C"
                                helpers.TempUnits.K -> "K"
                                helpers.TempUnits.F -> "°F"
                            }
                            expand = false
                        }) {
                            Text(text = label.toString())
                        }
                    }
                }
            }


        }
        Row() {
            OutlinedButton(
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(25.dp),
                onClick = {
                    locationViewModel.updateAllWeatherDb()
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Aktualizacja danych",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }

    }
}