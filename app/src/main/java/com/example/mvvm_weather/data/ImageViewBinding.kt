package com.example.mvvm_weather.data

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBinding {
    private const val baseUrl = "http://openweathermap.org/img/wn"
    @JvmStatic
    @BindingAdapter("icon")
    fun loadImage(imageView: ImageView, icon : String?) {
        if(icon == null) {
            imageView.visibility = View.GONE
        }else {
            imageView.visibility = View.VISIBLE
            Glide.with(imageView.context)
                .load("$baseUrl/$icon@4x.png")
                .centerInside()
                .into(imageView)
        }
    }
}