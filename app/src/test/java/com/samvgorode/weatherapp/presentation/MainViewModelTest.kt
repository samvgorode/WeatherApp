package com.samvgorode.weatherapp.presentation

import com.samvgorode.weatherapp.domain.GetWeatherUseCase
import com.samvgorode.weatherapp.presentation.main.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class MainViewModelTest : VmTest() {

    @Test
    fun `vm should not create request if input is empty`() = runBlocking {
        val input = ""
        val useCase = getWeather(input = input)
        val vm = MainViewModel(useCase)
        vm.input.set(input)
        vm.search()
        coVerify(exactly = 0) { useCase.invoke(input) }
    }

    @Test
    fun `vm should not create request and get response if input is not empty`() = runBlocking {
        val input = "Vilnius"
        val output: WeatherInCityUiModel = mockk()
        val useCase = getWeather(input = input, output = output)
        val vm = MainViewModel(useCase)
        vm.input.set(input)
        vm.search()
        coVerify(exactly = 1) { useCase.invoke(input) }
        assertEquals(vm.weatherModel.get(), output)
    }

    @Test
    fun `vm should not create request and get error if input is not empty`() = runBlocking {
        val input = "Vilnius"
        val output: Throwable = mockk()
        val useCase = getWeather(input = input, error = output)
        val vm = MainViewModel(useCase)
        vm.input.set(input)
        try {
            vm.search()
        } catch (e: Throwable) {
            assertEquals(e, output)
        }
        coVerify(exactly = 1) { useCase.invoke(input) }
    }

    private fun getWeather(
        input: String,
        output: WeatherInCityUiModel = mockk(),
        error: Throwable? = null
    ): GetWeatherUseCase {
        val useCase = mockk<GetWeatherUseCase>()
        if (error == null) coEvery { useCase(input) } returns output
        else coEvery { useCase(input) } throws error
        return useCase
    }
}