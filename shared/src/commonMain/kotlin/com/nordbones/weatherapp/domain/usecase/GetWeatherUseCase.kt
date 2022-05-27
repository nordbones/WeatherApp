package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
    suspend operator fun invoke(): Flow<ResultWrapper<Weather>>
}