package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherDomainMapper @Inject constructor() {

    fun mapTiUiModel(cityWeather1: WeatherInCity): WeatherInCityUiModel {
        val icon = "http://openweathermap.org/img/wn/${cityWeather1.iconName}@2x.png"
        val dateFormat = SimpleDateFormat("E\n dd", Locale.getDefault())
        val date = Date(cityWeather1.date ?: 0L)
        val dateStr = dateFormat.format(date)
        val celsiusSign = "\u00B0"
        return WeatherInCityUiModel(
            id = cityWeather1.id,
            name = cityWeather1.name.orEmpty(),
            icon = icon,
            description = cityWeather1.description.orEmpty(),
            date = dateStr,
            temperature = cityWeather1.temperature.toString() + celsiusSign,
            temperatureMin = cityWeather1.temperatureMin.toString() + celsiusSign,
            temperatureMax = cityWeather1.temperatureMax.toString() + celsiusSign,
        )
    }
}