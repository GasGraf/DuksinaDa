package com.example.forduksinada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forduksinada.utils.RetrofitInstance
import com.example.forduksinada.utils.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun getWeather() {
        viewModelScope.launch {
            try {
                val moscowWeather = RetrofitInstance.weatherApi.getWeather(
                    "Мытищи",
                    "159fd912fae769187444b23c5eb41e41"
                ).copy(name = "Мытищи")
                val taganrogWeather = RetrofitInstance.weatherApi.getWeather(
                    "Таганрог",
                    "159fd912fae769187444b23c5eb41e41"
                ).copy(name = "Таганрог")

                _weatherState.value = WeatherState.Success(listOf(moscowWeather, taganrogWeather))
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Error 404 pizda")
            }
        }
    }
}