package com.example.ckweather.models.iptolocation

data class Location(
    val calling_codes: List<String>,
    val capital: String,
    val flag: String,
    val native_name: String,
    val top_level_domains: List<String>
)
