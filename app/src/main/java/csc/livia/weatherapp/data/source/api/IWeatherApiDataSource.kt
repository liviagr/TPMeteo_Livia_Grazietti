package csc.livia.weatherapp.data.source.api

import csc.livia.weatherapp.data.City
import csc.livia.weatherapp.data.WeatherDetails

interface IWeatherApiDataSource {
    suspend fun getLocationFromCity(city: String): Result<City>
    suspend fun getWeatherFromCity(city: City): Result<WeatherDetails>


}