package com.nordbones.weatherapp.data.responses.WeatherResponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("apparentTemperature")
    val apparentTemperature: Double,
    @SerialName("cloudCover")
    val cloudCover: Double,
    @SerialName("dewPoint")
    val dewPoint: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("icon")
    val icon: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double,
    @SerialName("ozone")
    val ozone: Double,
    @SerialName("pressure")
    val pressure: Double,
    @SerialName("summary")
    val summary: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("time")
    val time: Int,
    @SerialName("uvIndex")
    val uvIndex: Double? =null,
    @SerialName("visibility")
    val visibility: Double,
    @SerialName("windBearing")
    val windBearing: Double,
    @SerialName("windGust")
    val windGust: Double,
    @SerialName("windSpeed")
    val windSpeed: Double
)
