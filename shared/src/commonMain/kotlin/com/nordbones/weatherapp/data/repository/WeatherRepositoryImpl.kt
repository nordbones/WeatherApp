package com.nordbones.weatherapp.data.repository

import com.nordbones.weatherapp.data.dto.WeatherDTO
import com.nordbones.weatherapp.data.localsource.PreferencesStorage
import com.nordbones.weatherapp.data.remotesource.KtorClient
import com.nordbones.weatherapp.data.responses.WeatherResponse.asWeatherDTO
import com.nordbones.weatherapp.domain.repository.WeatherRepository
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val client: KtorClient,
    private val preferencesStorage: PreferencesStorage
) : WeatherRepository {
    override suspend fun getWeatherFromLocalStore(
        longitude: Double,
        latitude: Double,
    ): Flow<ResultWrapper<WeatherDTO>> =
        flow {
            emit(
                client.getWeather(longitude = longitude, latitude = latitude).asWeatherDTO()
            )
        }.asResult()

    override suspend fun getWeatherFromLocalStore(): WeatherDTO {
       return preferencesStorage.getWeather()
    }

    override suspend fun saveWeatherToLocalStore(weather: WeatherDTO) {
        preferencesStorage.saveWeather(weather)
    }


}