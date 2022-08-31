package com.mandroid.weatherapplib.data.client

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Manish Kumar on 31/08/22.
 */
class WeatherClient {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        val retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

}