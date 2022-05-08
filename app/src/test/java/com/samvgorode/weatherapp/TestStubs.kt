package com.samvgorode.weatherapp

import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.domain.WeatherDomainMapper
import com.samvgorode.weatherapp.domain.WeatherRepository
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

object TestStubs {

    fun getWeatherRepository(
        input: String = "",
        output: WeatherInCity = mockk(),
        output1: List<WeatherInCity> = listOf()
    ) = mockk<WeatherRepository> {
        coEvery { getWeatherForCity(input) } returns output
        coEvery { getWeatherList() } returns output1
    }

    fun getWeatherRepositoryError(
        input: String = "",
        output: Throwable = mockk()
    ) = mockk<WeatherRepository> {
        coEvery { getWeatherForCity(input) } throws  output
        coEvery { getWeatherList() } throws  output
    }

    fun getWeatherMapper(
        input: List<WeatherInCity> = listOf(),
        output: List<WeatherInCityUiModel> = listOf(),
    ) = mockk<WeatherDomainMapper> {
        input.forEachIndexed { index, weatherInCity ->
            every { mapToUiModel(weatherInCity) } returns output.get(index)
        }
    }
}