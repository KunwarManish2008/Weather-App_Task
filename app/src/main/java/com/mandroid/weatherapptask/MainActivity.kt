package com.mandroid.weatherapptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.mandroid.weatherapplib.data.callbacks.ForecastResponseCallback
import com.mandroid.weatherapplib.data.callbacks.WeatherResponseCallback
import com.mandroid.weatherapplib.data.remote.models.WeatherDto
import com.mandroid.weatherapplib.data.remote.models.WeatherForecastDto
import com.mandroid.weatherapplib.implementation.WeatherLibHelper

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherProvider = WeatherLibHelper(BuildConfig.API_KEY)

        findViewById<Button>(R.id.b1).setOnClickListener {
            weatherProvider.getCurrentWeatherByCity("delhi", object : WeatherResponseCallback {
                override fun onSuccess(currentWeather: WeatherDto) {
                    Log.d("resp", "$currentWeather")
                }

                override fun onFailure(throwable: Throwable) {
                    throwable.message?.let { it1 -> Log.d("resp", it1) }
                }
            })

        }

        findViewById<Button>(R.id.b2).setOnClickListener {

            weatherProvider.getCurrentWeatherByLocation(139.0,
                35.0,
                object : WeatherResponseCallback {
                    override fun onSuccess(currentWeather: WeatherDto) {
                        Log.d("resp", "$currentWeather")
                    }

                    override fun onFailure(throwable: Throwable) {
                        throwable.message?.let { it1 -> Log.d("resp", it1) }
                    }
                })
        }

        findViewById<Button>(R.id.b3).setOnClickListener {
            weatherProvider.getWeatherForecastByCity("Delhi", 6, object : ForecastResponseCallback {
                override fun onSuccess(currentWeather: WeatherForecastDto) {
                    Log.d("resp", "$currentWeather")
                }

                override fun onFailure(throwable: Throwable?) {
                    if (throwable != null) {
                        throwable.message?.let { it1 -> Log.d("resp", it1) }
                    }
                }

            })
        }

        findViewById<Button>(R.id.b4).setOnClickListener {
            weatherProvider.getWeatherForecastByLocation(139.0,
                35.0,
                6,
                object : ForecastResponseCallback {
                    override fun onSuccess(currentWeather: WeatherForecastDto) {
                        Log.d("resp", "$currentWeather")
                    }

                    override fun onFailure(throwable: Throwable?) {
                        if (throwable != null) {
                            throwable.message?.let { it1 -> Log.d("resp", it1) }
                        }
                    }

                })
        }
    }

}