package com.example.forduksinada.view.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.forduksinada.R
import com.example.forduksinada.utils.WeatherState
import com.example.forduksinada.viewmodel.WeatherViewModel
import java.util.Date

/**
 * экран с элементами  погоды
 */
@Composable
fun MainScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val time = Date()
    val isDay: Boolean = (time.hours in 6..17)
    val weatherState = weatherViewModel.weatherState.collectAsState().value
    val painter = if (isDay) painterResource(id = R.drawable.background_day_cat)
    else painterResource(id = R.drawable.background_cat_night)
    val index = (time.date * (time.month + 1)) % weatherViewModel.compliment.size

    LaunchedEffect(Unit) {
        weatherViewModel.getWeather()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                when (weatherState) {
                    is WeatherState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is WeatherState.Success -> {
                        weatherState.weather.forEachIndexed() { index, weather ->
                            WeatherItem(
                                state = weather,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                alignment = if (index == 0) Alignment.Start else Alignment.End
                            )
                        }
                    }

                    is WeatherState.Error -> {}
                }
            }
            MessageItem(
                text = weatherViewModel.compliment[index],
                color = Color(0xE6000000),
                background = Color(0x80FFFFFF)
            )
        }
    }
}





