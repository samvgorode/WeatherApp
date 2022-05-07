package com.samvgorode.weatherapp.presentation

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.samvgorode.weatherapp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeather: GetWeatherUseCase
    ): ViewModel() {

    val input = ObservableField<String>()
    val weatherModel = ObservableField<WeatherInCityUiModel>()
    val showWeatherWidget = ObservableBoolean()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("", "CoroutineExceptionHandler got $exception")
    }

    fun search() {
        input.get()?.takeIf { it.isNotBlank() }?.let {
            viewModelScope.launch(handler) {
                val city = getWeather(it)
                showWeatherWidget.set(true)
                weatherModel.set(city)
                Log.d("", city.toString())
            }
        }
    }
}