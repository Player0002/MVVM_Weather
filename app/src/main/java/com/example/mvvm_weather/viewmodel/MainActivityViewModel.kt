package com.example.mvvm_weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_weather.data.weather.Weather
import com.example.mvvm_weather.repository.LocationRepository
import com.example.mvvm_weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(
    locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val location = locationRepository.locationValue

    private var weather = MutableLiveData<Weather>()
    val weatherValue: LiveData<Weather> get() = weather

    suspend fun getWeather() {
        location.value?.let {
            val response = getWeatherWithLocation(it.latitude, it.longitude)
            viewModelScope.launch(Dispatchers.Main) {
                weather.value = response
            }
        }
    }

    private suspend fun getWeatherWithLocation(latitude: Double, longitude: Double): Weather {
        lateinit var weather: Weather
        try {
            val response = weatherRepository.getWeather(latitude, longitude)
            val body = response.body()

            body?.let {
                weather = body
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return weather
    }
}