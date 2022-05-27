package com.nordbones.weatherapp.domain.model

import com.nordbones.weatherapp.data.dto.LocationDTO

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val longitude: Double,
    val latitude: Double
)

fun LocationDTO.asLocation(): Location = Location(
    name = name,
    region = region,
    country = country,
    longitude = longitude,
    latitude = latitude
)

fun Location.asDTO(): LocationDTO = LocationDTO(
    name = name,
    region = region,
    country = country,
    longitude = longitude,
    latitude = latitude
)