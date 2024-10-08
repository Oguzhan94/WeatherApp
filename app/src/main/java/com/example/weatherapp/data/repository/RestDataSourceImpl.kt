package com.example.weatherapp.data.repository

import com.example.weatherapp.data.toWeather
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(
    private val weatherRestApi: WeatherRestApi
): WeatherRepository {
    override suspend fun getWeather(city: String, apiKey: String): Weather {
        return weatherRestApi.getWeather(city, apiKey).toWeather()
    }
}