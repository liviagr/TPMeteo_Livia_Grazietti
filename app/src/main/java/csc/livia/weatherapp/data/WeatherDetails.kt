package csc.livia.weatherapp.data

data class WeatherDetails(
//    val latitude: Double,
//    val longitude: Double,
//    val generationtime_ms: Double,
//    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
//    val elevation: Int,
    val current_units: CurrentUnits,
    val current: Current,
    val daily_units: DailyUnits,
    val daily: Daily
)

data class CurrentUnits(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val precipitation: String,
    val rain: String,
    val snowfall: String,
    val wind_speed_10m: String
)

data class Current(
    val time: String,
    val interval: Int,
    val temperature_2m: Double,
    val precipitation: Double,
    val rain: Double,
    val snowfall: Double,
    val wind_speed_10m: Double,
)

data class DailyUnits(
    val time: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String
)

data class Daily(
    val time: List<String>,
    val temperature_2m_max: List<Double?>,
    val temperature_2m_min: List<Double?>
)
