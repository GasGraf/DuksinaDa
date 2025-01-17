package com.example.forduksinada.view.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forduksinada.R
import com.example.forduksinada.utils.WeatherResponse
import java.util.Date
import kotlin.math.roundToInt


@Composable
fun WeatherItem(
    state: WeatherResponse,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal
) {
    val time = Date()
    val (backgroundRes, textColor) = if (time.hours in 6..17) {
        when (state.weather.firstOrNull()?.main) {
            "Clouds" -> R.drawable.background_cloudy_day to Color.White
            "Rain" -> R.drawable.background_cloudy_day to Color.White
            "Snow" -> R.drawable.backgroud_snow_day to Color.White
            else -> R.drawable.background_clear_sky to Color.White
        }
    } else {
        R.drawable.backgrounb_night to Color.White
    }
    val imagePainter = painterResource(id = backgroundRes)
    Box(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            painter = imagePainter,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 20.dp, bottom = 50.dp),
            horizontalAlignment = alignment
        ) {
            Text(
                text = state.name,
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                text = "Температура: ${state.main.temp.roundToInt()}°C",
                fontSize = 15.sp,
                color = textColor
            )
        }
    }
}