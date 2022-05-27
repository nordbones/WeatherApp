package com.nordbones.weatherapp.data.remotesource

import com.nordbones.weatherapp.data.responses.LocationSearchResponse.LocationResponse
import com.nordbones.weatherapp.data.responses.WeatherResponse.WeatherResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val WEATHER_API_KEY = "b4c031d64688de8d4a9024df72da494fc77e3a8f7701a64322dd19c0a57766cc"
private const val LOCATION_API_KEY = "addacba8721162849fcdd58740180441"

class KtorClient {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun searchLocation(value: String): LocationResponse = client.get {
        url(LOCATION_SEARCH)
        parameter("access_key", LOCATION_API_KEY)
        parameter("query", value)
    }.body()

    suspend fun getWeather(longitude: Double,latitude:Double): WeatherResponse = client.get {
        headers{
            append("x-api-key", WEATHER_API_KEY)
        }
        url(GET_WEATHER)
        parameter("lat",latitude)
        parameter("lng",longitude)
    }.also { println("-->> $it") }.body()

    private companion object Routes {
        private const val BASE_URL_WEATHER = " https://api.ambeedata.com/weather/latest"
        private const val BASE_URL_LOCATION = "http://api.positionstack.com/v1"
        const val LOCATION_SEARCH = "$BASE_URL_LOCATION/forward"
        const val GET_WEATHER = "$BASE_URL_WEATHER/by-lat-lng"
    }

}