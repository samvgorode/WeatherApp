package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherMapper: WeatherDomainMapper
) {

    suspend operator fun invoke(query: String): WeatherInCityUiModel {
        val dbCity = weatherRepository.getWeatherForCity(query)
        return weatherMapper.mapTiUiModel(dbCity)
    }
}