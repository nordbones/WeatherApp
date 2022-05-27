package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.domain.model.asLocations
import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchLocationUseCaseImpl(private val repository: LocationRepository) :
    SearchLocationUseCase {
    override suspend fun invoke(location: String): Flow<ResultWrapper<Locations>> =
        repository.searchLocation(location)
            .map { result ->
                result.onSuccess { it.asLocations() }
            }
}