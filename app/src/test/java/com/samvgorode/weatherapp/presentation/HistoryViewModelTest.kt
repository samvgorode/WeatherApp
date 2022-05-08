package com.samvgorode.weatherapp.presentation

import com.samvgorode.weatherapp.domain.GetWeatherHistoryUseCase
import com.samvgorode.weatherapp.presentation.history.HistoryViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class HistoryViewModelTest : VmTest() {

    @Test
    fun `vm should not create request on init and get response`() = runBlocking {
        val output: List<WeatherInCityUiModel> = listOf()
        val useCase = getWeatherHistory(output)
        val vm = HistoryViewModel(useCase)
        coVerify { useCase.invoke() }
        assertEquals(vm.weatherList.get(), output)
    }

    @Test
    fun `vm should not create request on init and throw error`() = runBlocking {
        val output: Throwable = mockk()
        val useCase = getWeatherHistory(error = output)
        try {
            HistoryViewModel(useCase)
        } catch (e: Throwable) {
            assertEquals(e, output)
        }
        coVerify { useCase.invoke() }
    }

    private fun getWeatherHistory(
        output: List<WeatherInCityUiModel> = listOf(),
        error: Throwable? = null
    ): GetWeatherHistoryUseCase {
        val useCase = mockk<GetWeatherHistoryUseCase>()
        if (error == null) coEvery { useCase() } returns output
        else coEvery { useCase() } throws error
        return useCase
    }
}