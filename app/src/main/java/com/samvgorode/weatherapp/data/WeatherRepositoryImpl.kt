package com.samvgorode.weatherapp.data

import com.samvgorode.weatherapp.data.local.WeatherDao
import com.samvgorode.weatherapp.data.local.WeatherDatabase
import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.data.remote.ApiService
import com.samvgorode.weatherapp.data.remote.WeatherResponse
import com.samvgorode.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao,
    private val weatherMapper: WeatherDataMapper
) : WeatherRepository {
    override suspend fun getWeatherForCity(cityName: String): WeatherInCity =
        withContext(Dispatchers.IO) {
            val cityWeather: WeatherResponse = apiService.getWeather(cityName, APP_ID)
            val dbWeather = weatherMapper.mapWeather(cityWeather)
            weatherDao.insertWeather(dbWeather)
            dbWeather
        }

    override suspend fun getWeatherList(): List<WeatherInCity> = withContext(Dispatchers.IO) {
        weatherDao.getAll()
    }

    private companion object {
        const val APP_ID = "7587eaff3affbf8e56a81da4d6c51d06"
    }
}