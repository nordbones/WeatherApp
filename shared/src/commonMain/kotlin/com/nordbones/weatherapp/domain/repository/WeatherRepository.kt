package com.nordbones.weatherapp.domain.repository

import com.nordbones.weatherapp.data.dto.WeatherDTO
import com.nordbones.weatherapp.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherFromLocalStore(longitude: Double, latitude: Double): Flow<ResultWrapper<WeatherDTO>>

    suspend fun saveWeatherToLocalStore(weather: WeatherDTO)

    suspend fun getWeatherFromLocalStore(): WeatherDTO
}