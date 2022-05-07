package com.samvgorode.weatherapp.data

import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.data.remote.WeatherResponse
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapWeather(cityWeather: WeatherResponse): WeatherInCity {
        val weather = cityWeather.weather.firstOrNull()
        val main = cityWeather.main
        return WeatherInCity(
            id = cityWeather.id?.toInt() ?: 0,
            name = cityWeather.name,
            iconName = weather?.icon,
            description = weather?.description,
            date = cityWeather.dt,
            temperature = main?.temp?.toDouble(),
            temperatureMin = main?.tempMin?.toDouble(),
            temperatureMax = main?.tempMax?.toDouble(),
        )
    }
}