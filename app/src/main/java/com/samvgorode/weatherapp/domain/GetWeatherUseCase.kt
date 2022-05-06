package com.samvgorode.weatherapp.domain

class GetWeatherUseCase {

    operator fun invoke(query: String): WeatherForCity = WeatherForCity()
}