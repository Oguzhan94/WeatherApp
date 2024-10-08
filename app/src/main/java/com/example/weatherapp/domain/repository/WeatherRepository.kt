package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): Weather
}