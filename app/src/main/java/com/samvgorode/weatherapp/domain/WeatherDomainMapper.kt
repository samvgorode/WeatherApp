package com.samvgorode.weatherapp.domain

import androidx.annotation.VisibleForTesting
import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherDomainMapper @Inject constructor() {

    fun mapToUiModel(cityWeather: WeatherInCity): WeatherInCityUiModel {
        val celsiusSign = "\u00B0"
        return WeatherInCityUiModel(
            id = cityWeather.id,
            name = cityWeather.name.orEmpty(),
            icon = mapToWeatherIcon(cityWeather.iconName),
            description = cityWeather.description.orEmpty(),
            date = mapToUiDate(cityWeather.date),
            temperature = cityWeather.temperature.toString() + celsiusSign,
            temperatureMin = cityWeather.temperatureMin.toString() + celsiusSign,
            temperatureMax = cityWeather.temperatureMax.toString() + celsiusSign,
        )
    }

    @VisibleForTesting
    fun mapToUiDate(date: Long?): String {
        val dateFormat = SimpleDateFormat("E\n dd", Locale.getDefault())
        val dateObj = Date((date ?: 0L) * 1000)
        return dateFormat.format(dateObj)
    }

    @VisibleForTesting
    fun mapToWeatherIcon(iconName: String?) =
        "http://openweathermap.org/img/wn/${iconName}@2x.png"
}