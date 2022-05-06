package com.samvgorode.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.samvgorode.weatherapp.data.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: ApiService): ViewModel() {

    val input = ObservableField<String>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("", "CoroutineExceptionHandler got $exception")
    }

    fun search() {
        input.get()?.takeIf { it.isNotBlank() }?.let {
            viewModelScope.launch(handler) {
                val city = api.getWeather(it, "7587eaff3affbf8e56a81da4d6c51d06")
                Log.d("", city.toString())
            }
        }
    }
}