package com.example.mvvm_weather.data.weather

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.roundToInt

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<WeatherXX>,
    val wind_deg: Int,
    val wind_speed: Double
) {
    fun tempStr() = "${temp.day.roundToInt()}℃"
    fun dateStr() =
        SimpleDateFormat("yyyy년 MM월 dd일 (E)")
            .format(
                Calendar.getInstance().apply {
                    timeInMillis = dt.toLong() * 1000
                }.time
            ).let {
                var result = it
                val strMap = HashMap<String, String>().apply {
                    put("Sun", "일")
                    put("Mon", "월")
                    put("Tue", "화")
                    put("Wed", "수")
                    put("Thu", "목")
                    put("Fri", "금")
                    put("Sat", "토")
                }
                strMap.forEach { key, value ->
                    if (it.contains(key)) result = it.replace(key, value)
                }
                result
            }

}