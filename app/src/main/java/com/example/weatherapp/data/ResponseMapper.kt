package com.example.weatherapp.data

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.data.model.WeatherResponse

fun WeatherResponse.toWeather(): Weather {
    return Weather(
        cityName = this.name,
        temperature = this.main.temp,
        feelsLike = this.main.feelsLike,
        description = this.weather.firstOrNull()?.description ?: "",
        icon = this.weather.firstOrNull()?.icon ?: "",
        humidity = this.main.humidity,
        windSpeed = this.wind.speed,
        country = this.sys.country,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset
    )
}