package com.example.ckweather.models.iptolocation

data class IpToLocation(
    val city: String,
    val connection: Connection,
    val continent_code: String,
    val continent_name: String,
    val country_code: String,
    val country_name: String,
    val currencies: List<Currency>,
    val ip: String,
    val is_eu: Boolean,
    val latitude: Double,
    val location: Location,
    val longitude: Double,
    val region_name: String,
    val timezones: List<String>,
    val type: String
)
