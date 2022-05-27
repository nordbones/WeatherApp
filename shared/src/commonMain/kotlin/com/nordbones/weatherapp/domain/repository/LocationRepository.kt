package com.nordbones.weatherapp.domain.repository

import com.nordbones.weatherapp.data.dto.LocationDTO
import com.nordbones.weatherapp.data.dto.LocationsDTO
import com.nordbones.weatherapp.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun searchLocation(value: String): Flow<ResultWrapper<LocationsDTO>>
    suspend fun saveLocation(location: LocationDTO)
    suspend fun getLocation(): LocationDTO
}