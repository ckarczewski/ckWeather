package com.example.ckweather.views.location

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ckweather.R
import com.example.ckweather.views.location.searchLocation.SearchLocationItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocationScreen(
    navController: NavController
){
    val locationViewModel: LocationViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()
    Box() {
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
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            var searchText by remember {
                mutableStateOf(TextFieldValue(""))
            }

            Text(
                text = "Dodaj nowe miasto",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        vertical = 20.dp
                    )
            )

            OutlinedTextField(
                modifier = Modifier.padding(vertical = 10.dp),
                label = {
                    Text(
                        text = "Zajdź miasto",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.Black
                ),
                value = searchText,
                onValueChange = {
                    searchText = it
                    searchViewModel.clearSearchList()
                    searchViewModel.fetchGeocoding(it.text)
                },
            )
            if(searchText.text != ""){
                SearchLocationItem(
                    locationViewModel,
                    clearInputText = {
                        searchText = TextFieldValue("")
                        keyboardController?.hide()
                    })
            }
        }
    }
    Log.d("location screen","dupa2")
//TODO: change name from LocationScreen on SearhLocationScreen
}