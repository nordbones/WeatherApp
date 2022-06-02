package com.nordbones.weatherapp.domain.model

import com.nordbones.weatherapp.data.dto.LocationDTO
import com.nordbones.weatherapp.data.dto.WeatherDTO


data class Weather(
    val description: String,
    val windSpeedInMpH: Double,
    val windDirectionCompass: String,
    val windDirection: Double,
    val temp: Int,
    val airPressure: Double,
    val humidityInPercents: Float,
    val visibilityInMiles: Double,
    val apparentTemp: Double,
    val name: String,
    val region: String,
    val country: String,
    val longitude: Double,
    val latitude: Double
) {

    companion object {
        fun fromDTO(weatherDTO: WeatherDTO, locationDTO: LocationDTO): Weather = Weather(
            description = weatherDTO.description,
            windSpeedInMpH = weatherDTO.windSpeedInMpH,
            windDirection = weatherDTO.windDirection,
            windDirectionCompass = convertDegreesToCompassDirection(weatherDTO.windDirection),
            temp = weatherDTO.temp.toInt(),
            airPressure = weatherDTO.airPressure,
            humidityInPercents = weatherDTO.humidityInPercents,
            visibilityInMiles = weatherDTO.visibilityInMiles,
            apparentTemp = weatherDTO.apparentTemp,
            name = locationDTO.name,
            region = locationDTO.region,
            country = locationDTO.country,
            longitude = locationDTO.longitude,
            latitude = locationDTO.latitude
        )

        private fun convertDegreesToCompassDirection(degrees: Double): String {
            val sector = 360 / 4
            val halfOfSector: Double = (sector / 2).toDouble()
            return when (degrees) {
                in (0 - halfOfSector)..(0 + halfOfSector) -> "North"
                in (sector - halfOfSector)..(sector + halfOfSector) -> "East"
                in (sector * 2 - halfOfSector)..(sector * 2 + halfOfSector) -> "South"
                in (sector * 3 - halfOfSector)..(sector * 3 + halfOfSector) -> "West"
                else -> "None"
            }
        }
    }
}

