package csc.livia.weatherapp.ui.weatherDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.compose.ui.tooling.data.EmptyGroup.location
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import csc.livia.weatherapp.data.Current
import csc.livia.weatherapp.data.CurrentUnits
import csc.livia.weatherapp.data.Daily
import csc.livia.weatherapp.data.DailyUnits
import csc.livia.weatherapp.ui.utils.TopAppBar


@Composable
fun WeatherDetailsScreen (
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
    modifier :Modifier = Modifier,
    navigateBack: () -> Unit,


){

    val uiState: WeatherDetailUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var checked by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { uiState.city?.let { TopAppBar(it.name, navigateBack) } })
    { innerPadding ->
    Column(modifier = modifier.padding(innerPadding)) {
        Row()
        {
            Text("Add to favorite")
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    viewModel.addToFavortie(checked)
                }
            )
        }


        CurrentWeather(current = uiState.weatherResult?.current, units = uiState.weatherResult?.current_units)

        WeatherPrediction(daily = uiState.weatherResult?.daily, dailyUnits = uiState.weatherResult?.daily_units)
    }
        }
}

@Composable
fun CurrentWeather(current: Current?, units: CurrentUnits?) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Column(modifier = Modifier.padding(PaddingValues(16.dp))) {
            if(current != null && units != null) {
                Text("${current.temperature_2m} ${units.temperature_2m}",
                fontSize = 30.sp, fontWeight = FontWeight.Bold)
                WeatherCondition("Precipitation", current.precipitation, units.precipitation)
                WeatherCondition("Rain", current.rain, units.rain)
                WeatherCondition("Snowfall", current.snowfall, units.snowfall)

                Text("Wind Speed: ${current.wind_speed_10m} ${units.wind_speed_10m}")
            } else {
                Text("Can't display current weather")
            }
        }
    }
}

@Composable
fun WeatherCondition(weatherConditionName: String, weatherConditionValue: Double, weatherConditionUnit: String ){
    if(weatherConditionValue > 0){
        Text("$weatherConditionName: $weatherConditionValue $weatherConditionUnit")
    }

}



@Composable
fun WeatherPrediction(daily: Daily?, dailyUnits: DailyUnits?) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Column {
            if(daily != null && dailyUnits != null) {
                    daily.time.forEachIndexed { index, date ->
                        val maxTemp = daily.temperature_2m_max.getOrNull(index)
                        val minTemp = daily.temperature_2m_min.getOrNull(index)
                        if (maxTemp != null && minTemp != null) {
                            DailyWeather(date, maxTemp, minTemp, dailyUnits.temperature_2m_max)
                        }
                    }
            } else {
                Text("Unavailable")
            }
        }
    }

}

@Composable
fun DailyWeather(date : String, maxTemp: Double, minTemp: Double, dailyUnits: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(16.dp))) {
            Text(date)
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(horizontal = 8.dp))
            {
                Text("$minTemp $dailyUnits")
                Text(" - ")
                Text("$maxTemp $dailyUnits")
            }
        }
    }
}

