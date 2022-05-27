package com.nordbones.weatherapp.domain.usecase

import com.nordbones.weatherapp.domain.model.Location

interface GetLocationUseCase {
    suspend operator fun invoke(): Location
}

