package com.mandroid.weatherapplib.data.remote

import com.mandroid.weatherapplib.data.remote.models.WeatherDto
import com.mandroid.weatherapplib.data.remote.models.WeatherForecastDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Manish Kumar on 31/08/22.
 */

interface WeatherService {

    @GET("weather")
    fun getWeatherDataByCity(@QueryMap params: Map<String, String>): Call<WeatherDto>

    @GET("weather")
    fun getWeatherDataByLocation(@QueryMap params: Map<String, String>): Call<WeatherDto>

    @GET("forecast/daily")
    fun getForecastDataByCity(@QueryMap params: Map<String, String>): Call<WeatherForecastDto>

    @GET("forecast/daily")
    fun getForecastDataByLocation(@QueryMap params: Map<String, String>): Call<WeatherForecastDto>

}