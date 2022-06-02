package com.nordbones.weatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val name: String,
    val region: String,
    val country: String,
    val longitude: Double,
    val latitude: Double
)


