package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.domain.repository.WeatherRepository
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.onSuccess
import com.nordbones.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.*

class GetWeatherUseCaseImpl constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) :
    GetWeatherUseCase {
    override suspend fun invoke(): Flow<ResultWrapper<Weather>> {
        val location = locationRepository.getLocation()
        return weatherRepository.getWeather(
            longitude = location.longitude,
            latitude = location.latitude
        ).map { result ->
            result.onSuccess { weatherDTO ->
                Weather(
                    description = weatherDTO.description,
                    windSpeedInMpH = weatherDTO.windSpeedInMpH,
                    windDirection = weatherDTO.windDirection,
                    windDirectionCompass = convertDegreesToCompassDirection(weatherDTO.windDirection),
                    temp = weatherDTO.windDirection,
                    airPressure = weatherDTO.airPressure,
                    humidityInPercents = weatherDTO.humidityInPercents,
                    visibilityInMiles = weatherDTO.visibilityInMiles,
                    apparentTemp = weatherDTO.apparentTemp,
                    name = location.name,
                    region = location.region,
                    country = location.country,
                    longitude = location.longitude,
                    latitude = location.latitude
                )
            }
        }

    }

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
