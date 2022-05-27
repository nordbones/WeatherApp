package com.nordbones.weatherapp.data.dto

import com.nordbones.weatherapp.utils.EMPTY

data class WeatherDTO(
    val description: String,
    val windSpeedInMpH: Double,
    val windDirection: Double,
    val temp: Double,
    val airPressure: Double,
    val humidityInPercents: Float,
    val visibilityInMiles: Double,
    val apparentTemp:Double
) {
    companion object {
        val empty
            get() = WeatherDTO(
                description = String.EMPTY,
                windSpeedInMpH = Double.NaN,
                windDirection = Double.NaN,
                temp = Double.NaN,
                airPressure = Double.NaN,
                humidityInPercents = Float.NaN,
                visibilityInMiles = Double.NaN,
                apparentTemp = Double.NaN
            )
    }
}
