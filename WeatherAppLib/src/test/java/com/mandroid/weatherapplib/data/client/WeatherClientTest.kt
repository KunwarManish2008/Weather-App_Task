package com.mandroid.weatherapplib.data.client

import com.mandroid.weatherapplib.data.remote.WeatherService
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Manish Kumar on 01/09/22.
 */
class WeatherClientTest{

    @Test
    fun `retrofit client should not be null`(){
        val service = WeatherClient.getClient().create(WeatherService::class.java)
        assert(service != null)
    }
}