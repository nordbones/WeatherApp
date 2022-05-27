package com.nordbones.weatherapp.domain.model

import com.nordbones.weatherapp.data.dto.LocationsDTO

data class Locations(val list: List<Location>)


fun Locations.asDTO(): LocationsDTO = LocationsDTO(list.map { it.asDTO() })

fun LocationsDTO.asLocations(): Locations = Locations(list.map { it.asLocation() })