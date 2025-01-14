package csc.livia.weatherapp.ui.weatherDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import csc.livia.weatherapp.data.City
import csc.livia.weatherapp.data.WeatherRepository
import csc.livia.weatherapp.data.WeatherDetails
import csc.livia.weatherapp.data.source.local.WeatherDao
import csc.livia.weatherapp.data.toLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class WeatherDetailUiState(
    var city: City? = null,
    val weatherResult: WeatherDetails? = null,
    val isLoading : Boolean = false,
)

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,

    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val city: String = savedStateHandle["city"]!!

    private val _uiState = MutableStateFlow(WeatherDetailUiState())
    val uiState: StateFlow<WeatherDetailUiState> = _uiState

    init {
        // Start loading weather data as soon as the ViewModel is created
        fetchWeatherForCity()
    }

    private fun fetchWeatherForCity() {
        // Update the UI state to show loading
        _uiState.value = _uiState.value.copy(isLoading = true)

        // Coroutine to fetch city information and weather details
        viewModelScope.launch {
            try {
                val city = Gson().fromJson(city, City::class.java)
//                println(city)
                val weatherDetails = weatherRepository.getWeatherFromCity(city).getOrNull()
//
//                // Update UI state with city and weather result
                _uiState.value = WeatherDetailUiState(
                    city = city,
                    weatherResult = weatherDetails,
                    isLoading = false
                )
            } catch (e: Exception) {
                // Handle any exception during the API call and update UI state
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun addToFavortie(checked: Boolean){
        _uiState.value.city = _uiState.value.city?.copy(favorite = checked)
        if (checked && uiState.value.city != null) {
            viewModelScope.launch {

                weatherRepository.addCityToFavorite(uiState.value.city!!)
            }
        }

    }
}