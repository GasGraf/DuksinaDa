package com.example.forduksinada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forduksinada.utils.RetrofitInstance
import com.example.forduksinada.utils.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherState = MutableStateFlow<Map<String, WeatherState>>(emptyMap())
    val weatherState: StateFlow<Map<String, WeatherState>> = _weatherState

    fun getWeather() {
        viewModelScope.launch {
            _weatherState.value = mapOf(
                "Мытищи" to WeatherState.Loading,
                "Таганрог" to WeatherState.Loading
            )
            try {
                val moscowWeather = RetrofitInstance.weatherApi.getWeather(
                    "Мытищи",
                    "159fd912fae769187444b23c5eb41e41"
                )
                val taganrogWeather = RetrofitInstance.weatherApi.getWeather(
                    "Таганрог",
                    "159fd912fae769187444b23c5eb41e41"
                )
                _weatherState.value = mapOf(
                    "Мытищи" to WeatherState.Success(moscowWeather),
                    "Таганрог" to WeatherState.Success(taganrogWeather)
                )
            } catch (e: Exception) {
                _weatherState.value = mapOf(
                    "Мытищи" to WeatherState.Error(e.message ?: "Ошибка при получении данных"),
                    "Таганрог" to WeatherState.Error(e.message ?: "Ошибка при получении данных")
                )
            }
        }
    }
}