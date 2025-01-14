package csc.livia.weatherapp.data

import csc.livia.weatherapp.data.source.api.IWeatherApiDataSource
import csc.livia.weatherapp.data.source.local.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
                         private val apiDataSource: IWeatherApiDataSource,
                         private val localDataSource: WeatherDao

) : IWeatherRepository {

    override suspend fun getLocationFromCity(city: String): Result<City> {
       return withContext(Dispatchers.IO) {
            apiDataSource.getLocationFromCity(city)
       }
    }

    override suspend fun getWeatherFromCity(city: City): Result<WeatherDetails> {
        return withContext(Dispatchers.IO) {
            apiDataSource.getWeatherFromCity(city)
        }
    }

    override suspend fun addCityToFavorite(city: City) {
        localDataSource.upsert(city.toLocal())
    }


}
