package com.example.forduksinada.view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.forduksinada.utils.WeatherResponse
import com.example.forduksinada.utils.WeatherState
import com.example.forduksinada.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState = weatherViewModel.weatherState.collectAsState().value

    LaunchedEffect(Unit) {
        weatherViewModel.getWeather()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (weatherState) {
            is WeatherState.Loading -> {
                CircularProgressIndicator()
            }

            is WeatherState.Success -> {
                weatherState.weather.forEachIndexed() { index, weather ->

                    WeatherItem(
                        state = weather,
                        modifier = Modifier.weight(1f),
                        alignment = if (index == 0) Alignment.Start else Alignment.End
                    )

                }
            }

            is WeatherState.Error -> {}
        }
    }
}

@Composable
fun WeatherItem(
    state: WeatherResponse,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal
) {

    Column(modifier = modifier.background(Color.Green), horizontalAlignment = alignment) {
        Text(
            text = state.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
        Text(text = "Температура: ${state.main.temp}°C", fontSize = 15.sp)
        Text(text = "Влажность: ${state.main.humidity}%", fontSize = 14.sp)
    }
}



