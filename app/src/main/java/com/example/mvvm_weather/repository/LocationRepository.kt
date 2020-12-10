package com.example.mvvm_weather.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvm_weather.data.LocationInfo
import com.example.mvvm_weather.livedata.LocationLiveData

class LocationRepository(context: Context) {

    private var locationData = LocationLiveData(context)

    val locationValue : LiveData<LocationInfo> get() = locationData

}