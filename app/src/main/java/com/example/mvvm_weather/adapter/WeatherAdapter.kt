package com.example.mvvm_weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_weather.R
import com.example.mvvm_weather.data.weather.Daily
import com.example.mvvm_weather.databinding.WeatherItemBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>() {
    private val weatherList = ArrayList<Daily>()

    fun updateItem(list : List<Daily>) {
        weatherList.clear()
        weatherList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : WeatherItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.weather_item, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size
}
class WeatherViewHolder(private val binding : WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(daily: Daily) {
        with(binding) {
            items = daily
        }
    }
}