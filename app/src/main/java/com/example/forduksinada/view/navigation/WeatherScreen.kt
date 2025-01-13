package com.example.forduksinada.view.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.forduksinada.R
import com.example.forduksinada.utils.WeatherResponse
import com.example.forduksinada.utils.WeatherState
import com.example.forduksinada.viewmodel.WeatherViewModel

/**
 * экран с элементами  погоды
 */
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState = weatherViewModel.weatherState.collectAsState().value
    val imagePainter = painterResource(id = R.drawable.sun)
    LaunchedEffect(Unit) {
        weatherViewModel.getWeather()
    }
    Column (Modifier.fillMaxSize()){
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
                            alignment = if (index == 0) Alignment.Start else Alignment.End,
                            imagePainter
                        )

                    }
                }

                is WeatherState.Error -> {}
            }
        }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.fillMaxHeight(1f)) {
            Text(
                "Лисима́х (др.-греч. Λυσίμαχος; ок. 361, Пелла — 281 до н. э.) — сподвижник Александра Македонского, диадох, правитель Фракии с 323 года до н. э., царь Македонии с 285 года до н. э. Лисимах родился и вырос в семье выходца из Фессалии Агафокла, который поступил на службу к македонскому царю Филиппу II. Ещё при жизни Филиппа II юного Лисимаха назначили телохранителем-соматофилаком царевича и наследника престола Александра. В этом качестве он прошёл весь Восточный поход Александра Македонского, отличился своей храбростью, был ранен в Индии.\n" +
                        "\n" +
                        "После смерти Александра в 323 году до н. э. Лисимах получил, в изложении Юстина «как храбрейший из всех», в управление самую неспокойную провинцию Македонской империи — Фракию. Вначале его власть распространялась лишь на прибрежные земли возле Чёрного и Эгейского морей. Первые двадцать лет он был занят войнами против фракийцев, придунайских скифов, гетов и бунтовавших греческих полисов, которые проходили с переменным успехом. Во время одного из походов он даже попал в плен к правителю гетов Дромихету, который счёл более выгодным для себя отпустить высокопоставленного пленника домой. Вплоть до 302 года до н. э., хотя Лисимах и участвовал в многочисленных и кровопролитных войнах диадохов за наследство Александра, его роль была незначительной. Владения Лисимаха находились на окраине Македонской империи и не вызывали особого интереса у амбициозных диадохов. Ситуация изменилась во время Четвёртой войны диадохов. К этому времени Лисимах смог собрать сильное войско, а также аккумулировать в казне необходимые для ведения войны средства. В союзе с другими диадохами Селевком и Кассандром он участвовал в битве при Ипсе 301 года до н. э., во время которой погиб Антигон I Одноглазый. При дележе наследства Антигона Лисимах получил в управление большие и богатые территории в Малой Азии. Впоследствии он воевал с наследником Антигона Деметрием Полиоркетом и царём Эпира Пирром. В результате этих войн Лисимах в 285 году до н. э. присоединил к своим владениям Македонию и стал одним из самых могущественных правителей Средиземноморья.\n" +
                        "\n" +
                        "В 283/282 году до н. э. Лисимах приказал убить своего старшего сына и наследника Агафокла. В историографии существует несколько версий такого поступка: интрига молодой жены Лисимаха Арсинои, которая хотела обеспечить престол своим детям; неудавшийся заговор со стороны Агафокла. За смертью Агафокла последовал кризис, которым не преминул воспользоваться Селевк. Он вторгся во владения Лисимаха, официально с целью восстановления справедливости по просьбе вдовы Агафокла Лисандры. Это усилило раскол и часть местной аристократии перешла на сторону Селевка. В последующей битве при Курупедионе в начале 281 года до н. э. Лисимах погиб на поле боя.\n" +
                        "\n" +
                        "При оценке личности Лисимаха античные и современные историки отмечали противоречивость его характера. Среди его положительных качеств выделяют храбрость, сдержанность, скромность, ум, увлечение философией, целеустремлённость и проницательность. Одновременно, в представлении историков, Лисимах был жесток, авторитарен, скуп и подвержен женскому влиянию. Благодаря такому сочетанию Лисимах смог практически с нуля создать одно из самых могущественных государств своего времени, которое перестало существовать вскоре после его смерти.",
                fontSize = 22.sp,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }
    }

}

@Composable
fun WeatherItem(
    state: WeatherResponse,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal,
    imagePainter: Painter
) {
    Column(
        modifier = modifier
            .paint(
                painter = imagePainter,
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            ),
        horizontalAlignment = alignment
    ) {
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




