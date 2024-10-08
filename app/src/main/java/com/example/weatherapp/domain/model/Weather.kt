package com.example.weatherapp.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val feelsLike: Double,
    val description: String,
    val icon: String,
    val humidity: Int,
    val windSpeed: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
