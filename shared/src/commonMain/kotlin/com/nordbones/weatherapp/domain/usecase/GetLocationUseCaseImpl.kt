package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.asLocation
import com.nordbones.weatherapp.domain.repository.LocationRepository

class GetLocationUseCaseImpl(private val locationRepository: LocationRepository) :
    GetLocationUseCase {
    override suspend fun invoke(): Location = locationRepository.getLocation().asLocation()
}