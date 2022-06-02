package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.domain.repository.WeatherRepository
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.onSuccess
import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.utils.onError
import kotlinx.coroutines.flow.*

class GetWeatherUseCaseImpl constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) :
    GetWeatherUseCase {
    override suspend fun invoke(): Flow<ResultWrapper<Weather>> {
        val locationDTO = locationRepository.getLocation()
        return weatherRepository.getWeatherFromLocalStore(
            longitude = locationDTO.longitude,
            latitude = locationDTO.latitude
        ).map { result ->
            result
                .onSuccess { weatherDTO ->
                    weatherRepository.saveWeatherToLocalStore(weatherDTO)
                    Weather.fromDTO(weatherDTO = weatherDTO, locationDTO = locationDTO)
                }.onError {
                    val weatherDTO = weatherRepository.getWeatherFromLocalStore()
                    Weather.fromDTO(weatherDTO = weatherDTO, locationDTO = locationDTO)
                }
        }
    }

}
