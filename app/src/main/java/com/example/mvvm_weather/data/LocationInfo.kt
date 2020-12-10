package com.example.mvvm_weather.data

data class LocationInfo(
    val longitude : Double,
    val latitude : Double,
    var name : String = "Loading",
)
