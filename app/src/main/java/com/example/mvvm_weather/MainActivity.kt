package com.example.mvvm_weather

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_weather.adapter.WeatherAdapter
import com.example.mvvm_weather.api.RetrofitClient
import com.example.mvvm_weather.databinding.ActivityMainBinding
import com.example.mvvm_weather.repository.LocationRepository
import com.example.mvvm_weather.repository.WeatherRepository
import com.example.mvvm_weather.util.PermissionUtil
import com.example.mvvm_weather.viewmodel.MainActivityViewModel
import com.example.mvvm_weather.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (!PermissionUtil.hasGpsPermission(this)) {
            PermissionUtil.requestGpsPermission(this)
        } else {
            initialization()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionUtil.LOCATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initialization()
                } else {
                    Toast.makeText(this, "Permission denied ", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun initialization() {
        val repository = LocationRepository(this)
        val weather = RetrofitClient.getWeatherService(RetrofitClient.getRetrofitInstance());
        val weatherRepository =
            WeatherRepository(apiKey = BuildConfig.API_KEY, weatherService = weather)
        val factory = MainActivityViewModelFactory(repository, weatherRepository)

        val owner = this;

        with(binding) {
            lifecycleOwner = owner;
            addressName.isSelected = true
            recyclerView.adapter = WeatherAdapter()
        }

        viewModel = ViewModelProvider(owner, factory).get(MainActivityViewModel::class.java)
        with(viewModel) {
            location.observe(owner) {
                progressBar.visibility = View.GONE
                binding.location = it;
                CoroutineScope(Dispatchers.IO).launch {
                    getWeather()
                }
            }
            weatherValue.observe(owner) {
                binding.weather = it
            }
        }

    }
}
