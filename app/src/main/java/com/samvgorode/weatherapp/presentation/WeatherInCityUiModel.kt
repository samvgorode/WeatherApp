package com.samvgorode.weatherapp.presentation

import androidx.databinding.ObservableField

data class WeatherInCityUiModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val date: String = "",
    val temperature: String = "",
    val temperatureMin: String = "",
    val temperatureMax: String = "",
)