package com.example.weatherapp.presentation

import com.example.weatherapp.domain.model.Weather

sealed class WeatherState {
    object Idle : WeatherState()
    object Loading : WeatherState()
    data class Success(val weather: Weather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}