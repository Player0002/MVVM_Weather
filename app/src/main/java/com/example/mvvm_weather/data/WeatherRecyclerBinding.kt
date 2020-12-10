package com.example.mvvm_weather.data

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_weather.adapter.WeatherAdapter
import com.example.mvvm_weather.data.weather.Daily

object WeatherRecyclerBinding {
    @JvmStatic
    @BindingAdapter("items")
    fun bindItems(recyclerView: RecyclerView, item : List<Daily>?) {
        recyclerView.adapter?.run {
            if(this is WeatherAdapter) {
                item?: return
                this.updateItem(item)
                this.notifyDataSetChanged()
            }
        }
    }
}