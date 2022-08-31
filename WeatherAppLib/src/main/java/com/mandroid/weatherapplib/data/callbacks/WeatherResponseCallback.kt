package com.mandroid.weatherapplib.data.callbacks

import com.mandroid.weatherapplib.data.remote.models.WeatherDto

/**
 * Created by Manish Kumar on 31/08/22.
 */
interface WeatherResponseCallback {
    fun onSuccess(currentWeather: WeatherDto)
    fun onFailure(throwable: Throwable)
}