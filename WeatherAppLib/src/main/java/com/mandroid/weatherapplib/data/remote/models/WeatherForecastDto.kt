package com.mandroid.weatherapplib.data.remote.models

import com.squareup.moshi.Json

/**
 * Created by Manish Kumar on 31/08/22.
 */

data class WeatherForecastDto(
    val city: City,
    val cnt: Long,
    val message: Double,
    val cod: String,
    val list: List<DayWeatherForecast>
)

data class DayWeatherForecast(
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,

    @Json(name = "feels_like")
    val feelsLike: FeelsLike,

    val pressure: Long,
    val humidity: Int,
    val speed: Double,
    val clouds: Int,
    val gust: Double,
    val weather: List<Weather>


)

data class Temp(
    @field:Json(name = "day")
    val day: Double,
    @field:Json(name = "eve")
    val eve: Double,
    @field:Json(name = "max")
    val max: Double,
    @field:Json(name = "min")
    val min: Double,
    @field:Json(name = "morn")
    val morn: Double,
    @field:Json(name = "night")
    val night: Double
)

data class Weather(
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "icon")
    val icon: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: String
)

data class City(
    @field:Json(name = "coord")
    val coord: Coord,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "population")
    val population: Int,
    @field:Json(name = "timezone")
    val timezone: Int
)

data class FeelsLike(
    @field:Json(name = "day")
    val day: Double,
    @field:Json(name = "eve")
    val eve: Double,
    @field:Json(name = "morn")
    val morn: Double,
    @field:Json(name = "night")
    val night: Double
)

