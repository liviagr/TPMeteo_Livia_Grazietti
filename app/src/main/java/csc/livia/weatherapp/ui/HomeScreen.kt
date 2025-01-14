package csc.livia.weatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import csc.livia.weatherapp.NavigationDestination
import csc.livia.weatherapp.R
import csc.livia.weatherapp.ui.searchBar.SearchBar
import csc.livia.weatherapp.ui.searchBar.SearchBarViewModel
import csc.livia.weatherapp.ui.utils.TopAppBar


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}


@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier, viewModel: SearchBarViewModel,
               onSearchTriggered: (String) -> Unit, navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(
            navigateBack = navigateBack

            ) })
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SearchBar(modifier, viewModel, onSearchTriggered)

        }}

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



