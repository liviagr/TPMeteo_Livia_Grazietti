package csc.livia.weatherapp.ui.searchBar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import csc.livia.weatherapp.data.City
import csc.livia.weatherapp.data.WeatherRepository
//import csc.livia.weatherapp.data.getLocationFromApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class SearchBarUiState(
    var location: City? = null,
//    val isSuccessful: Boolean
)

@HiltViewModel
class SearchBarViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository) : ViewModel(){


    private val _uiState = MutableStateFlow(SearchBarUiState())
    val uiState: StateFlow<SearchBarUiState> = _uiState


    suspend fun makeGeocodingApiRequest(city: String) {

        withContext(Dispatchers.IO) {
            val response = weatherRepository.getLocationFromCity(city)
            if(response.isSuccess){
                uiState.value.location = response.getOrNull()

                println(uiState.value.location)
            }

        }
    }


    fun getLocationFromCity(city: String) {
        viewModelScope.launch {

            _uiState.value.location = City(
                name = "Paris", latitude = 48.85341, longitude = 2.3488, favorite = false,
                id = 1
            )

            makeGeocodingApiRequest(city)
        }
    }
}