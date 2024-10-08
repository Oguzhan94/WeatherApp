package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRestApi {
    @GET("data/2.5/weather")
   suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ) : WeatherResponse
}
