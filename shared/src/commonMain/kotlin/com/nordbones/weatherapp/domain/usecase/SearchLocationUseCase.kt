package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface SearchLocationUseCase {
    suspend operator fun invoke(location: String): Flow<ResultWrapper<Locations>>
}