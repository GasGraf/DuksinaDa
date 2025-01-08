package com.example.forduksinada.view.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.forduksinada.utils.WeatherState
import com.example.forduksinada.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState = weatherViewModel.weatherState.collectAsState().value

    LaunchedEffect(Unit) {
        weatherViewModel.getWeather()
    }

    Row (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        weatherState.forEach { (city, state) ->
            when (state) {
                is WeatherState.Loading -> {
                    Text(text = "Загрузка погоды для $city...", fontSize = 20.sp)
                }
                is WeatherState.Error -> {
                    Text(text = "Ошибка при загрузке для $city: ${state.message}", color = Color.Red, fontSize = 20.sp)
                }
                is WeatherState.Success -> {
                    val weather = state.weather
                    Column {
                        Text(text = "$city", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Температура: ${weather.main.temp}°C", fontSize = 20.sp)
                        Text(text = "Влажность: ${weather.main.humidity}%", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

