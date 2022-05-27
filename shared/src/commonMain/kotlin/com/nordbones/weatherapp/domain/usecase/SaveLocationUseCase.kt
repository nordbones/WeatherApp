package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Location

interface SaveLocationUseCase {
    suspend operator fun invoke(location: Location)
}

