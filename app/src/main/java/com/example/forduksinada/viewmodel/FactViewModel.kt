package com.example.forduksinada.viewmodel

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.forduksinada.view.navigation.FactOfDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactViewModel : ViewModel() {

    private val _facts = MutableStateFlow<List<FactOfDay>>(emptyList())
    val facts: StateFlow<List<FactOfDay>> = _facts

    fun getFacts() {
        viewModelScope.launch {
            try {
                val loadedFacts = loadFactsFromRepository()
                _facts.value = loadedFacts
            } catch (e: Exception) {

                _facts.value = emptyList()
            }
        }
    }


    private suspend fun loadFactsFromRepository(): List<FactOfDay> {
        kotlinx.coroutines.delay(1000)
        return listOf(
            FactOfDay(id = 1, title = "Интересный факт", description = "Жирафы спят всего 30 минут в сутки."),
            FactOfDay(id = 2, title = "Знаете ли вы?", description = "Мед считается единственным продуктом, который не портится."),
            FactOfDay(id = 3, title = "Наука", description = "В вакууме звук не распространяется."),
            FactOfDay(id = 4, title = "Космос", description = "В солнечную систему входит 8 планет."),
        )
    }

}
