package com.mandroid.weatherapplib.data.remote.models

import com.squareup.moshi.Json

/**
 * Created by Manish Kumar on 31/08/22.
 */

data class WeatherDto(
    @field:Json(name = "base")
    val base: String,
    @field:Json(name = "clouds")
    val clouds: Clouds,
    @field:Json(name = "cod")
    val cod: Int,
    @field:Json(name = "coord")
    val coord: Coord,
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: Main,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "sys")
    val sys: Sys,
    @field:Json(name = "timezone")
    val timezone: Int,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "wind")
    val wind: Wind
)

data class Clouds(
    @field:Json(name = "all")
    val all: Int
)

data class Coord(
    @field:Json(name = "lat")
    val lat: Double,
    @field:Json(name = "lon")
    val lon: Double
)

data class Main(
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "humidity")
    val humidity: Int,
    @field:Json(name = "pressure")
    val pressure: Int,
    @field:Json(name = "temp")
    val temp: Double,
    @field:Json(name = "temp_max")
    val tempMax: Double,
    @field:Json(name = "temp_min")
    val tempMin: Double
)

data class Sys(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "message")
    val message: Double,
    @field:Json(name = "sunrise")
    val sunrise: Int,
    @field:Json(name = "sunset")
    val sunset: Int,
    @field:Json(name = "type")
    val type: Int
)

data class Wind(
    @field:Json(name = "deg")
    val deg: Int,
    @field:Json(name = "speed")
    val speed: Double
)
