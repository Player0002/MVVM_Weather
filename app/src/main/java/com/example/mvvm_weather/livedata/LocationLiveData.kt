package com.example.mvvm_weather.livedata

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvm_weather.data.LocationInfo
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*

class LocationLiveData(private val context: Context) : LiveData<LocationInfo>() {
    private var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var loc = LocationInfo(0.0, 0.0)
    override fun onActive() {
        super.onActive()
        value = loc
        startLocationUpdates()
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun setLocationData(location : Location) {
        val geocoder = Geocoder(context, Locale.KOREA)
        val locInfo = geocoder.getFromLocation(location.latitude, location.longitude, 1).first()
        val saveLocation = LocationInfo(location.longitude, location.latitude, locInfo.getAddressLine(0))
        if(loc != saveLocation) {
            loc = saveLocation
            value = saveLocation
        }
    }

    private val locationCallback = object  : LocationCallback() {
        override fun onLocationResult(loc: LocationResult?) {
            loc ?: return
            for (location in loc.locations) {
                setLocationData(location)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    companion object{
        val locationRequest : LocationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}