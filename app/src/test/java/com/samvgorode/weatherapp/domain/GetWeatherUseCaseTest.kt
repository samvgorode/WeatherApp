package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.TestStubs
import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetWeatherUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke should return proper value`() = runTest {
        val city = "Vilnius"
        val weatherFromDb = mockk<WeatherInCity>()
        val weatherUi = mockk<WeatherInCityUiModel>()
        val weatherHistory = listOf(weatherFromDb)
        val weatherUiHistory = listOf(weatherUi)
        val repository: WeatherRepository =
            TestStubs.getWeatherRepository(input = city, output = weatherHistory.first())
        val mapper = TestStubs.getWeatherMapper(weatherHistory, weatherUiHistory)
        val useCase = GetWeatherUseCase(repository, mapper)
        val result = useCase.invoke(city)
        coVerify { repository.getWeatherForCity(city) }
        verify(exactly = 1) { mapper.mapToUiModel(weatherFromDb) }
        Assert.assertEquals(result, weatherUi)
    }

    @Test
    fun `invoke should throw error if repository fails`() = runTest {
        val city = "Tallinn"
        val error = mockk<Throwable>()
        val repository: WeatherRepository =
            TestStubs.getWeatherRepositoryError(input = city, output = error)
        val mapper = TestStubs.getWeatherMapper()
        val useCase = GetWeatherUseCase(repository, mapper)
        try {
            useCase.invoke(city)
        } catch (e: Throwable) {
            Assert.assertEquals(e, error)
        }
        coVerify { repository.getWeatherForCity(city) }
        verify(exactly = 0) { mapper.mapToUiModel(any()) }
    }
}