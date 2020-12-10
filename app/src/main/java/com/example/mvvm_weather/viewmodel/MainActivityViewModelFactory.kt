package com.example.mvvm_weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_weather.repository.LocationRepository
import com.example.mvvm_weather.repository.WeatherRepository

class MainActivityViewModelFactory(
    private val repository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(repository, weatherRepository) as T
        throw IllegalArgumentException("This class is not assigned from main activity view model.")
    }
}