package com.samvgorode.weatherapp.data

import com.samvgorode.weatherapp.data.local.WeatherDao
import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.data.remote.ApiService
import com.samvgorode.weatherapp.data.remote.WeatherResponse
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    @Test
    fun `getWeatherForCity should return weather`() = runTest {
        val response = mockk<WeatherResponse>()
        val dbModel = mockk<WeatherInCity>()
        val apiService = getApiService(response)
        val weatherMapper = getWeatherMapper(response, dbModel)
        val weatherDao = getWeatherDao(dbModel)
        val repositoryImpl = WeatherRepositoryImpl(apiService, weatherDao, weatherMapper)
        val weather = repositoryImpl.getWeatherForCity("")

        coVerify { apiService.getWeather("", "7587eaff3affbf8e56a81da4d6c51d06") }
        verify { weatherMapper.mapWeather(response) }
        coVerify { weatherDao.insertWeather(dbModel) }

        assertEquals(weather, dbModel)
    }

    @Test
    fun `getWeatherList should return weather list`() = runTest {
        val weathersList = listOf<WeatherInCity>(mockk(), mockk(), mockk())
        val apiService = getApiService()
        val weatherMapper = getWeatherMapper()
        val weatherDao = getWeatherDao(output = weathersList)
        val repositoryImpl = WeatherRepositoryImpl(apiService, weatherDao, weatherMapper)
        val weathers = repositoryImpl.getWeatherList()

        coVerify(exactly = 0) { apiService.getWeather(any(), any()) }
        verify(exactly = 0) { weatherMapper.mapWeather(any()) }
        coVerify(exactly = 0) { weatherDao.insertWeather(any()) }
        coVerify { weatherDao.getAll() }

        assertEquals(weathers, weathersList)
    }

    private fun getApiService(output: WeatherResponse = mockk()) = mockk<ApiService> {
        coEvery { getWeather(any(), any()) } returns output
    }

    private fun getWeatherMapper(
        input: WeatherResponse = mockk(),
        output: WeatherInCity = mockk()
    ) = mockk<WeatherDataMapper> {
        every { mapWeather(input) } returns output
    }

    private fun getWeatherDao(
        input: WeatherInCity = mockk(),
        output: List<WeatherInCity> = listOf()
    ) = mockk<WeatherDao> {
        coEvery { insertWeather(input) } returns Unit
        coEvery { getAll() } returns output
    }
}