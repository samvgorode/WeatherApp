package com.samvgorode.weatherapp.presentation.history

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samvgorode.weatherapp.domain.GetWeatherHistoryUseCase
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getWeatherHistory: GetWeatherHistoryUseCase
) : ViewModel() {

    val weatherList = ObservableField<List<WeatherInCityUiModel>>()

    init {
        viewModelScope.launch {
            val history = getWeatherHistory()
            weatherList.set(history)
        }
    }
}