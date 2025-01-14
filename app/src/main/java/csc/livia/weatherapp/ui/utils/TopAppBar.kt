package csc.livia.weatherapp.ui.utils
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import csc.livia.weatherapp.NavigationDestination
import csc.livia.weatherapp.R
import csc.livia.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    text: String = "WeatherApp",
    navigateBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text,
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}