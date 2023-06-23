package com.example.ckweather.views.location.searchLocation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ckweather.models.geocoding.GeocodingItem
import com.example.ckweather.R

@Composable
fun LocationRow(
    geocodingItem: GeocodingItem,
    addNewLocationCallback: ((geocodingItem: GeocodingItem) -> Unit)? = null,
    clearInputText: (() -> Unit)? = null
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = 10.dp,
                ).background(color = Color(0xFF464141), shape = RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 5.dp
            )) {
                Text(
                    text = geocodingItem.name,
                    fontSize = 24.sp,
                    color = Color.White,
                )
                Text(
                    text = geocodingItem.state,
                    fontSize = 18.sp,
                    color = Color.White,
                )
                Text(
                    text = geocodingItem.country!!,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }
            val addIcon = ImageVector.vectorResource(id = R.drawable.add_icon)
            IconButton(onClick = {
                if (addNewLocationCallback != null) {
                    addNewLocationCallback(geocodingItem)
                }

                if(clearInputText != null) {
                    clearInputText()
                }
            }) {
                Icon(
                    imageVector = addIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color(0xFF696969)
                )
            }
        }

    }
}
@Preview
@Composable
fun LocationRowPreview(){
    LocationRow(
        GeocodingItem(
            name = "Łódź",
            state = "Łódzkie",
            country = "PL"
        )
    )
}