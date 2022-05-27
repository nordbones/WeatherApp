package com.nordbones.weatherapp.domain.model


data class Weather(
    val description: String,
    val windSpeedInMpH: Double,
    val windDirectionCompass: String,
    val windDirection: Double,
    val temp: Double,
    val airPressure: Double,
    val humidityInPercents: Float,
    val visibilityInMiles: Double,
    val apparentTemp: Double,
    val name: String,
    val region: String,
    val country: String,
    val longitude: Double,
    val latitude: Double
)