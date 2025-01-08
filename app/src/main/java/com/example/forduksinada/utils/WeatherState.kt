package com.example.forduksinada.utils


sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: List<WeatherResponse>) : WeatherState()
    data class Error(val message: String) : WeatherState()
}



