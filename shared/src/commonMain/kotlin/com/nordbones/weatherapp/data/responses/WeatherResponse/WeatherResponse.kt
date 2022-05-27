package com.nordbones.weatherapp.data.responses.WeatherResponse


import com.nordbones.weatherapp.data.dto.WeatherDTO
import com.nordbones.weatherapp.utils.fromFahrenheitToCelsius
import com.nordbones.weatherapp.utils.toPercents
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String
)

fun WeatherResponse.asWeatherDTO() =
    WeatherDTO(
        description = this.data.summary,
        temp = this.data.temperature.fromFahrenheitToCelsius(),
        humidityInPercents = this.data.humidity.toPercents().toFloat(),
        windDirection = this.data.windBearing,
        windSpeedInMpH = this.data.windSpeed,
        airPressure = this.data.pressure,
        visibilityInMiles = this.data.visibility,
        apparentTemp = this.data.apparentTemperature
    )
