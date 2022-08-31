package com.mandroid.weatherapplib.implementation

import com.mandroid.weatherapplib.data.callbacks.ForecastResponseCallback
import com.mandroid.weatherapplib.data.callbacks.WeatherResponseCallback
import com.mandroid.weatherapplib.data.client.WeatherClient
import com.mandroid.weatherapplib.data.remote.WeatherService
import com.mandroid.weatherapplib.data.remote.models.WeatherDto
import com.mandroid.weatherapplib.data.remote.models.WeatherForecastDto
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created by Manish Kumar on 31/08/22.
 */


class WeatherLibHelper(private val apiKey: String) {

    private val queryParams = mutableMapOf<String, String>()

    var service: WeatherService

    private val APPID = "appId"
    private val UNITS = "units"
    private val LANGUAGE = "lang"
    private val QUERY = "q"
    private val LATITUDE = "lat"
    private val LONGITUDE = "lon"
    private val COUNT = "cnt"

    init {
        queryParams[APPID] = apiKey
        service = WeatherClient.getClient().create(WeatherService::class.java)
    }

    fun setUnits(unit: String){
        queryParams[UNITS] = unit
    }

    fun setLanguage(lang : String){
        queryParams[LANGUAGE] = lang
    }

    fun getCurrentWeatherByCity(city: String, callback: WeatherResponseCallback){
        queryParams.clear()
        queryParams[APPID] = apiKey
        queryParams[QUERY] = city

        service.getWeatherDataByCity(queryParams).enqueue(object : Callback<WeatherDto> {
            override fun onResponse(call: Call<WeatherDto>, response: Response<WeatherDto>) {
                handleWeatherResponse(response, callback)
            }
            override fun onFailure(call: Call<WeatherDto>, throwable: Throwable) {
                callback.onFailure(throwable)
            }
        })

    }

    fun getCurrentWeatherByLocation(longitude: Double, latitude: Double, callback: WeatherResponseCallback){
        queryParams.clear()
        queryParams[APPID] = apiKey
        queryParams[LONGITUDE] = longitude.toString()
        queryParams[LATITUDE] = latitude.toString()

        service.getWeatherDataByLocation(queryParams).enqueue(object : Callback<WeatherDto> {
            override fun onResponse(call: Call<WeatherDto>, response: Response<WeatherDto>) {
                handleWeatherResponse(response, callback)
            }
            override fun onFailure(call: Call<WeatherDto>, throwable: Throwable) {
                callback.onFailure(throwable)
            }
        })
    }

    fun getWeatherForecastByCity(city: String, dayCount: Int, callback: ForecastResponseCallback){
        queryParams.clear()
        queryParams[APPID] = apiKey
        queryParams[QUERY] = city
        queryParams[COUNT] = dayCount.toString()

        service.getForecastDataByCity(queryParams).enqueue(object : Callback<WeatherForecastDto> {
            override fun onResponse(call: Call<WeatherForecastDto>, response: Response<WeatherForecastDto>) {
                handleForecastResponse(response, callback)
            }
            override fun onFailure(call: Call<WeatherForecastDto>, throwable: Throwable) {
                callback.onFailure(throwable)
            }
        })

    }

    fun getWeatherForecastByLocation(longitude: Double, latitude: Double, dayCount: Int, callback: ForecastResponseCallback){
        queryParams.clear()
        queryParams[APPID] = apiKey
        queryParams[LONGITUDE] = longitude.toString()
        queryParams[LATITUDE] = latitude.toString()
        queryParams[COUNT] = dayCount.toString()

        service.getForecastDataByLocation(queryParams).enqueue(object : Callback<WeatherForecastDto> {
            override fun onResponse(call: Call<WeatherForecastDto>, response: Response<WeatherForecastDto>) {
                handleForecastResponse(response, callback)
            }
            override fun onFailure(call: Call<WeatherForecastDto>, throwable: Throwable) {
                callback.onFailure(throwable)
            }
        })

    }

    private fun handleWeatherResponse(
        response: Response<WeatherDto>,
        callback: WeatherResponseCallback,
    ) {
        if (response.code() === HttpURLConnection.HTTP_OK) {
            response.body()?.let { callback.onSuccess(it) }
        } else if (response.code() === HttpURLConnection.HTTP_FORBIDDEN || response.code() === HttpURLConnection.HTTP_UNAUTHORIZED) {
            callback.onFailure(NoAppIdErrMessage())
        } else {
            try {
                response.errorBody()?.let { NotFoundErrorMessage(it.string()) }
                    ?.let { callback.onFailure(it) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun handleForecastResponse(
        response: Response<WeatherForecastDto>,
        callback: ForecastResponseCallback,
    ) {
        if (response.code() === HttpURLConnection.HTTP_OK) {
            response.body()?.let { callback.onSuccess(it) }
        } else if (response.code() === HttpURLConnection.HTTP_FORBIDDEN || response.code() === HttpURLConnection.HTTP_UNAUTHORIZED) {
            callback.onFailure(NoAppIdErrMessage())
        } else {
            try {
                callback.onFailure(response.errorBody()?.let { NotFoundErrorMessage(it.string()) })
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun NoAppIdErrMessage(): Throwable {
        return Throwable("UnAuthorized. Please set a valid OpenWeatherMap API KEY.")
    }


    private fun NotFoundErrorMessage(str: String): Throwable {
        var throwable: Throwable? = null
        try {
            val obj = JSONObject(str)
            throwable = Throwable(obj.getString("message"))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (throwable == null) {
            throwable = Throwable("An unexpected error occurred.")
        }
        return throwable
    }


}