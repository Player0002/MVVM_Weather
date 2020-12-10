package com.example.mvvm_weather.api

import com.example.mvvm_weather.data.weather.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("onecall")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey : String,
        @Query("units") units : String = "metric",
        @Query("lang") lang : String = "KR",
        @Query("exclude") exclude : String = "minutely,hourly"
    ): Response<Weather>

}