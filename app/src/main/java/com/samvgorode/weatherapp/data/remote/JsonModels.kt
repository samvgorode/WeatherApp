package com.samvgorode.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") var coord: Coord? = Coord(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("visibility") var visibility: Number? = null,
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("dt") var dt: Long? = null,
    @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("timezone") var timezone: Number? = null,
    @SerializedName("id") var id: Number? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Number? = null
)

data class Coord(
    @SerializedName("lon") var lon: Number? = null,
    @SerializedName("lat") var lat: Number? = null
)

data class Clouds(
    @SerializedName("all") var all: Number? = null
)

data class Main(
    @SerializedName("temp") var temp: Number? = null,
    @SerializedName("feels_like") var feelsLike: Number? = null,
    @SerializedName("temp_min") var tempMin: Number? = null,
    @SerializedName("temp_max") var tempMax: Number? = null,
    @SerializedName("pressure") var pressure: Number? = null,
    @SerializedName("humidity") var humidity: Number? = null
)

data class Sys(
    @SerializedName("type") var type: Number? = null,
    @SerializedName("id") var id: Number? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("sunrise") var sunrise: Number? = null,
    @SerializedName("sunset") var sunset: Number? = null
)

data class Weather(
    @SerializedName("id") var id: Number? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)

data class Wind(
    @SerializedName("speed") var speed: Number? = null,
    @SerializedName("deg") var deg: Number? = null
)

