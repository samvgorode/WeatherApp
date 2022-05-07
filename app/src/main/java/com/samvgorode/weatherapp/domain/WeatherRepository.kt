package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.data.local.WeatherInCity

interface WeatherRepository {
    suspend fun getWeatherForCity(cityName: String): WeatherInCity
    suspend fun getWeatherList(): List<WeatherInCity>
}