package csc.livia.weatherapp.data

interface IWeatherRepository {
    suspend fun getLocationFromCity(city: String): Result<City>
    suspend fun getWeatherFromCity(city: City): Result<WeatherDetails>
    suspend fun addCityToFavorite(city: City)


}