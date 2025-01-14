package csc.livia.weatherapp.data

data class GeocodingResponse(
    val results: List<City>
)


data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val favorite: Boolean,
    val id: Int
)
