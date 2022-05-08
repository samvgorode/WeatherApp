package com.samvgorode.weatherapp.presentation.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel

@BindingAdapter("srsCoil")
fun setSrsCoil(imageView: AppCompatImageView, srsCoil: String?) {
    if (srsCoil.isNullOrBlank().not()) imageView.load(srsCoil)
}

@BindingAdapter("weatherHistory")
fun setWeatherHistory(recyclerView: RecyclerView, weatherHistory: List<WeatherInCityUiModel>?) {
    if (weatherHistory.isNullOrEmpty().not()) {
        recyclerView.adapter = HistoryAdapter(weatherHistory!!)
    }
}