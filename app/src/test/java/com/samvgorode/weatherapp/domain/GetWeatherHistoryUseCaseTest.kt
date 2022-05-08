package com.samvgorode.weatherapp.domain

import com.samvgorode.weatherapp.TestStubs
import com.samvgorode.weatherapp.data.local.WeatherInCity
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherHistoryUseCaseTest {

    @Test
    fun `invoke should return proper value`() = runTest{
        val weatherFromDb = mockk<WeatherInCity>()
        val weatherUi = mockk<WeatherInCityUiModel>()
        val weatherHistory = listOf(weatherFromDb)
        val weatherUiHistory = listOf(weatherUi)
        val repository: WeatherRepository = TestStubs.getWeatherRepository(output1 = weatherHistory)
        val mapper = TestStubs.getWeatherMapper(weatherHistory, weatherUiHistory)
        val useCase = GetWeatherHistoryUseCase(repository, mapper)
        val result = useCase.invoke()
        coVerify { repository.getWeatherList() }
        verify(exactly = 1) { mapper.mapToUiModel(weatherFromDb) }
        assertEquals(result.first(), weatherUi)
    }

    @Test
    fun `invoke should return proper list size`() = runTest{
        val weatherHistory = listOf<WeatherInCity>(mockk(), mockk(), mockk())
        val weatherUIHistory = listOf<WeatherInCityUiModel>(mockk(), mockk(), mockk())
        val repository: WeatherRepository = TestStubs.getWeatherRepository(output1 = weatherHistory)
        val mapper = TestStubs.getWeatherMapper(weatherHistory, weatherUIHistory)
        val useCase = GetWeatherHistoryUseCase(repository, mapper)
        val result = useCase.invoke()
        coVerify { repository.getWeatherList() }
        verify(exactly = 3) { mapper.mapToUiModel(any()) }
        assertEquals(result.size, weatherHistory.size)
    }

    @Test
    fun `invoke should throw error if repository fails`() = runTest{
        val error = mockk<Throwable>()
        val repository: WeatherRepository = TestStubs.getWeatherRepositoryError(output = error)
        val mapper = TestStubs.getWeatherMapper()
        val useCase = GetWeatherHistoryUseCase(repository, mapper)
        try{
            useCase.invoke()
        } catch (e: Throwable) {
            assertEquals(e, error)
        }
        coVerify { repository.getWeatherList() }
        verify(exactly = 0) { mapper.mapToUiModel(any()) }
    }
}