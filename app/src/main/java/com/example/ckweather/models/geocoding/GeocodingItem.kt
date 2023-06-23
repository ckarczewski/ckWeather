package com.example.ckweather.models.geocoding

import com.google.gson.annotations.SerializedName

data class GeocodingItem(
    @SerializedName("country")
    val country: String? = null,
    val lat: Double? = null,
    val local_names: LocalNames? = null,
    val lon: Double? = null,
    val name: String,
    var state: String
)
