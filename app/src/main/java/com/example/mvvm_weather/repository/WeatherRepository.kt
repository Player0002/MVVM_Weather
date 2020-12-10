package com.example.mvvm_weather.repository

import com.example.mvvm_weather.api.WeatherService
import com.example.mvvm_weather.data.weather.Weather
import retrofit2.Response

class WeatherRepository(private val weatherService: WeatherService, private val apiKey: String) {
    suspend fun getWeather(latitude: Double, longitude: Double): Response<Weather> =
        weatherService.getCurrentWeather(latitude, longitude, apiKey)
}