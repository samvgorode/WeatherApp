package com.samvgorode.weatherapp.domain

data class WeatherForCity(
    val description: String = "",
    val temperature: String = "",
    val temperatureMin: String = "",
    val temperatureMax: String = "",
    val date: String = "",
    val name: String = "",
)