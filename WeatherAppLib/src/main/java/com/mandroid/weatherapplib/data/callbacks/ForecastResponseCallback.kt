package com.mandroid.weatherapplib.data.callbacks

import com.mandroid.weatherapplib.data.remote.models.WeatherForecastDto

/**
 * Created by Manish Kumar on 31/08/22.
 */

interface ForecastResponseCallback {

        fun onSuccess(weatherForecast: WeatherForecastDto)
        fun onFailure(throwable: Throwable?)
}