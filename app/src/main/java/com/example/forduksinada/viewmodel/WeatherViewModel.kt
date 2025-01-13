package com.example.forduksinada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forduksinada.ApplicationConstants.Companion.mytishchi_sity
import com.example.forduksinada.ApplicationConstants.Companion.taganrog_sity
import com.example.forduksinada.ApplicationConstants.Companion.api_key_for_weather
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
                    mytishchi_sity,
                    api_key_for_weather
                ).copy(name = mytishchi_sity)
                val taganrogWeather = RetrofitInstance.weatherApi.getWeather(
                    taganrog_sity,
                    api_key_for_weather
                ).copy(name = taganrog_sity)

                _weatherState.value = WeatherState.Success(listOf(moscowWeather, taganrogWeather))
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Error not internet connection")
            }
        }
    }
}