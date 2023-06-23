package com.example.ckweather.views.location.searchLocation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ckweather.models.geocoding.GeocodingItem
import com.example.ckweather.views.location.LocationViewModel
import com.example.ckweather.views.location.SearchViewModel

@Composable
fun SearchLocationItem(
    locationViewModel: LocationViewModel,
    clearInputText: (()->Unit)? = null
){
    val searchViewModel: SearchViewModel = viewModel()
    val locationViewModel: LocationViewModel = viewModel()
    val geocodingList = searchViewModel.geocodingList.collectAsState(initial = emptyList())
    SearchLocationLazyColumn(geocodingList = geocodingList.value, locationViewModel, clearInputText)

}

@Composable
fun SearchLocationLazyColumn(
    geocodingList: List<GeocodingItem>,
    locationVm: LocationViewModel,
    clearInputText: (() -> Unit)? = null
) {
    if(geocodingList.isNotEmpty()){
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)){
            items(items = geocodingList){
                    item -> SearchLocationRow(geocodingItem = item, locationVm, clearInputText)
            }
        }
    }


}
@Composable
fun SearchLocationRow(
        geocodingItem: GeocodingItem,
        locationVm: LocationViewModel,
        clearInputText: (() -> Unit)? = null
    ){
    if(geocodingItem.state == null){
        geocodingItem.state = ""
    }
    LocationRow(
        geocodingItem,
        clearInputText = clearInputText,
        addNewLocationCallback = { AddNewLocation(geocodingItem, locationVm) })
}
fun AddNewLocation(
    geocodingItem: GeocodingItem,
    locationVm: LocationViewModel,
    ){
//    val locationItem = LocationItem(
//        name = geocodingItem.name,
//        lat = geocodingItem.lat,
//        lon = geocodingItem.lon,
//    )
//
//    locationVm.insertWeather(locationItem)
}