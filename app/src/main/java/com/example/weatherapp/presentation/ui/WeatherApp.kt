package com.example.weatherapp.presentation.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.WeatherState
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()
    val apiKey = BuildConfig.API_KEY
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var city by remember { mutableStateOf("") }
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city name") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { viewModel.getWeather(city, apiKey) }
            ) {
                Text("Get Weather")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box() {
                when (val state = weatherState) {
                    is WeatherState.Loading -> CircularProgressIndicator()
                    is WeatherState.Success -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("City: ${state.weather.cityName}, ${state.weather.country}")
                            Text("Temperature: ${state.weather.temperature}°C")
                            Text("Feels like: ${state.weather.feelsLike}°C")
                            Text("Description: ${state.weather.description}")
                            Text("Humidity: ${state.weather.humidity}%")
                            Text("Wind Speed: ${state.weather.windSpeed} m/s")
                            Text("Sunrise: ${formatTime(state.weather.sunrise)}")
                            Text("Sunset: ${formatTime(state.weather.sunset)}")
                        }
                    }
                    is WeatherState.Error -> Text(state.message, color = Color.Red)
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun formatTime(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

@Preview
@Composable
fun WeatherAppPreview() {
    val weatherRepository = FakeWeatherRepository()
    val getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    val viewModel = WeatherViewModel(getWeatherUseCase)
    WeatherApp(viewModel)
}
class FakeWeatherRepository : WeatherRepository {

    override suspend fun getWeather(city: String, apiKey: String): Weather {
        return Weather(
            cityName = "Sample City",
            temperature = 22.5,
            feelsLike = 23.0,
            description = "Clear Sky",
            icon = "01d",
            humidity = 60,
            windSpeed = 5.0,
            country = "US",
            sunrise = 1663939200,
            sunset = 1663982400
        )
    }
}