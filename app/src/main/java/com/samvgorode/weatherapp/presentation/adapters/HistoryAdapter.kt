package com.samvgorode.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samvgorode.weatherapp.databinding.WeatherWidgetBinding
import com.samvgorode.weatherapp.presentation.WeatherInCityUiModel

class HistoryAdapter(val list: List<WeatherInCityUiModel>) :
    RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        WeatherWidgetBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::HistoryViewHolder)


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        list.getOrNull(position)?.let(holder::bind)
    }

    override fun getItemCount(): Int = list.size

}

class HistoryViewHolder(private val binding: WeatherWidgetBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: WeatherInCityUiModel) {
        binding.weatherModel = weather
    }
}