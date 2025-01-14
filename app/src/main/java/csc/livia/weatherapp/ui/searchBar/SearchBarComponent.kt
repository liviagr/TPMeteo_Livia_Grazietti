package csc.livia.weatherapp.ui.searchBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson

@Composable
fun SearchBar(modifier: Modifier = Modifier,
              viewModel: SearchBarViewModel,
              onSearchTriggered: (String) -> Unit
) {
    var city by remember { mutableStateOf(TextFieldValue("")) }


    val uiState: SearchBarUiState by
    viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.padding(16.dp)) {
        SearchBarComponent(city) { newQuery ->
            city = newQuery


        }
        Button(
            onClick = {
                // Trigger the search when the button is clicked
                viewModel.getLocationFromCity(city.text)
                val cityJson = Gson().toJson(uiState.location) // Serialize city to JSON
                onSearchTriggered(cityJson)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun SearchBarComponent(query: TextFieldValue, onQueryChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Search...")
        },
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
        ),
        singleLine = true,
    )
}
