package com.nordbones.weatherapp.data.responses.LocationSearchResponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("administrative_area")
    val administrativeArea: String?,
    @SerialName("confidence")
    val confidence: Double?,
    @SerialName("continent")
    val continent: String?,
    @SerialName("country")
    val country: String,
    @SerialName("country_code")
    val countryCode: String?,
    @SerialName("county")
    val county: String?,
    @SerialName("label")
    val label: String?,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("locality")
    val locality: String?,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("name")
    val name: String,
    @SerialName("neighbourhood")
    val neighbourhood: String?,
    @SerialName("number")
    val number: String?,
    @SerialName("postal_code")
    val postalCode: String?,
    @SerialName("region")
    val region: String,
    @SerialName("region_code")
    val regionCode: String?,
    @SerialName("street")
    val street: String?,
    @SerialName("type")
    val type: String?
)