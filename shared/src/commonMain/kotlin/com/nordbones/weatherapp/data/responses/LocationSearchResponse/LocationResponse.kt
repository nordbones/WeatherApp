package com.nordbones.weatherapp.data.responses.LocationSearchResponse


import com.nordbones.weatherapp.data.dto.LocationDTO
import com.nordbones.weatherapp.data.dto.LocationsDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("data")
    val `data`: List<Data>
)

fun LocationResponse.asLocationsDTO(): LocationsDTO =
    LocationsDTO(
        this.data.map { it.asLocationDTO() }
    )

fun Data.asLocationDTO(): LocationDTO =
    LocationDTO(
        name = name,
        region = region,
        country = country,
        longitude = longitude,
        latitude = latitude
    )