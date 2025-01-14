package csc.livia.weatherapp.data.source.api


import csc.livia.weatherapp.data.WeatherDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,precipitation,rain,snowfall,wind_speed_10m",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("models") models: String = "meteofrance_seamless"
    ): Response<WeatherDetails>
}

