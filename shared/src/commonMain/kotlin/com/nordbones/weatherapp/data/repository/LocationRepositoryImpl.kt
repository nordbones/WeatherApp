package com.nordbones.weatherapp.data.repository

import com.nordbones.weatherapp.data.dto.LocationDTO
import com.nordbones.weatherapp.data.dto.LocationsDTO
import com.nordbones.weatherapp.data.localsource.PreferencesStorage
import com.nordbones.weatherapp.data.remotesource.KtorClient
import com.nordbones.weatherapp.data.responses.LocationSearchResponse.asLocationsDTO
import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl(
    private val client: KtorClient,
    private val preferencesStorage: PreferencesStorage
) : LocationRepository {
    override suspend fun searchLocation(value: String): Flow<ResultWrapper<LocationsDTO>> =
        flow {
            emit(
                client.searchLocation(value = value).asLocationsDTO()
            )
        }.asResult()

    override suspend fun saveLocation(location: LocationDTO) {
        preferencesStorage.saveLocation(location)
    }

    override suspend fun getLocation(): LocationDTO = preferencesStorage.getLocation()
}