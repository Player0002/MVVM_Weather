package com.example.mvvm_weather.data.weather

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind_deg: Int,
    val wind_speed: Double
)