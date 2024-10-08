package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Idle)
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    fun getWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val weather = getWeatherUseCase(city, apiKey)
                _weatherState.value = WeatherState.Success(weather)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Failed")
            }
        }
    }
}