package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import javax.inject.Inject

class GetWeatherHistoryUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherMapper: WeatherDomainMapper
) {

    suspend operator fun invoke(): List<WeatherInCityUiModel> {
        val dbCities = weatherRepository.getWeatherList()
        return dbCities.map(weatherMapper::mapToUiModel)
    }
}