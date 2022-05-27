package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.asDTO
import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.domain.usecase.SaveLocationUseCase

class SaveLocationUseCaseImpl(private val locationRepository: LocationRepository) :
    SaveLocationUseCase {
    override suspend fun invoke(location: Location) {
        locationRepository.saveLocation(location = location.asDTO())
    }
}