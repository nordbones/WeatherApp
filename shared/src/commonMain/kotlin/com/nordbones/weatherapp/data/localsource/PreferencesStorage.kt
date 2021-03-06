package com.nordbones.weatherapp.data.localsource

import com.nordbones.weatherapp.data.dto.LocationDTO
import com.nordbones.weatherapp.data.dto.WeatherDTO
import com.nordbones.weatherapp.utils.trim
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val LOCATION_LONGITUDE_KEY = "location_longitude_key"
private const val LOCATION_LATITUDE_KEY = "location_latitude_key"
private const val LOCATION_NAME_KEY = "location_name_key"
private const val LOCATION_REGION_KEY = "location_region_key"
private const val LOCATION_COUNTRY_KEY = "location_country_key"

private const val WEATHER_KEY = "weather_key"

class PreferencesStorage(private val context: SPreferences) {

    fun saveLocation(location: LocationDTO) {
        context.setStringValue(LOCATION_NAME_KEY, location.name)
        context.setStringValue(LOCATION_REGION_KEY, location.region)
        context.setStringValue(LOCATION_COUNTRY_KEY, location.country)
        context.setFloatValue(LOCATION_LONGITUDE_KEY, location.longitude.toFloat())
        context.setFloatValue(LOCATION_LATITUDE_KEY, location.latitude.toFloat())
    }

    fun getLocation(): LocationDTO = LocationDTO(
        name = context.getStringValue(LOCATION_NAME_KEY),
        region = context.getStringValue(LOCATION_REGION_KEY),
        country = context.getStringValue(LOCATION_COUNTRY_KEY),
        latitude = context.getFloatValue(LOCATION_LATITUDE_KEY).toDouble().trim(2),
        longitude = context.getFloatValue(LOCATION_LONGITUDE_KEY).toDouble().trim(2)
    )

    fun saveWeather(weatherDTO: WeatherDTO) {
        val jsonWeather: String = Json.encodeToString(weatherDTO)
        context.setStringValue(WEATHER_KEY, jsonWeather)
    }

    fun getWeather():WeatherDTO{
        val json = context.getStringValue(WEATHER_KEY)
        return Json.decodeFromString(json)
    }
}