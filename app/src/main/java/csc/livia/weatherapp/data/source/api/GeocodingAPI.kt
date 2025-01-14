package csc.livia.weatherapp.data.source.api


import csc.livia.weatherapp.data.GeocodingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {
    @GET("v1/search")
    suspend fun getLocation(
        @Query("name") name: String,
        @Query("count") count: Int,
        @Query("language") language: String
    ): Response<GeocodingResponse>
}