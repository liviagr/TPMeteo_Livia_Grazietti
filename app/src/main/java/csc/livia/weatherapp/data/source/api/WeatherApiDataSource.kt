package csc.livia.weatherapp.data.source.api

import csc.livia.weatherapp.data.City
import csc.livia.weatherapp.data.WeatherDetails
import javax.inject.Inject

class WeatherApiDataSource @Inject constructor() : IWeatherApiDataSource {
    private val geoCodingRetrofit = createRetrofit("https://geocoding-api.open-meteo.com/")
    private val geocodingApiService = geoCodingRetrofit.create(GeocodingApi::class.java)

    private val weatherRetrofit = createRetrofit("https://api.open-meteo.com/")
    private val weatherApiService = weatherRetrofit.create(WeatherApi::class.java)


    override suspend fun getLocationFromCity(city: String): Result<City> {

        val response = geocodingApiService.getLocation(city, 1, "fr")
        if(response.isSuccessful && response.body() != null){
            return if (response.body()!!.results.isEmpty()) {
                Result.failure(Exception("Result array is empty"))
            } else {
                Result.success(response.body()!!.results[0])
            }
        }
        return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
    }

    override suspend fun getWeatherFromCity(city: City): Result<WeatherDetails> {
        val latitude = city.latitude
        val longitude = city.longitude

        val response = weatherApiService.getWeatherForecast(
            latitude = latitude,
            longitude = longitude
        )
        if (response.isSuccessful && response.body() != null) {
            return Result.success(response.body()!!)
        }

        return Result.failure(Exception("Error: ${response.code()} ${response.message()}"))


    }

}