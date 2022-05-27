package com.nordbones.weatherapp

import com.nordbones.weatherapp.data.localsource.PreferencesStorage
import com.nordbones.weatherapp.data.localsource.SPreferences
import com.nordbones.weatherapp.data.remotesource.KtorClient
import com.nordbones.weatherapp.data.repository.LocationRepositoryImpl
import com.nordbones.weatherapp.data.repository.WeatherRepositoryImpl
import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.domain.repository.WeatherRepository
import com.nordbones.weatherapp.domain.usecase.*
import com.nordbones.weatherapp.viewmodel.LocationSharedViewModel
import com.nordbones.weatherapp.viewmodel.WeatherSharedViewModel


class WeatherModule(context: SPreferences) {
    private val ktorClient: KtorClient by lazy {
        KtorClient()
    }

    private val preferencesStorage: PreferencesStorage by lazy {
        PreferencesStorage(context = context)
    }

    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepositoryImpl(client = ktorClient)
    }

    private val locationRepository: LocationRepository by lazy {
        LocationRepositoryImpl(
            client = ktorClient,
            preferencesStorage = preferencesStorage
        )
    }

    private val getWeatherUseCase: GetWeatherUseCase by lazy {
        GetWeatherUseCaseImpl(
            weatherRepository = weatherRepository,
            locationRepository = locationRepository
        )
    }

    private val searchLocationUseCase: SearchLocationUseCase by lazy {
        SearchLocationUseCaseImpl(repository = locationRepository)
    }

    private val saveLocationUseCase: SaveLocationUseCase by lazy {
        SaveLocationUseCaseImpl(locationRepository = locationRepository)
    }

    private val getLocationUseCase: GetLocationUseCase by lazy {
        GetLocationUseCaseImpl(locationRepository = locationRepository)
    }

    val weatherSharedViewModel: WeatherSharedViewModel by lazy {
        WeatherSharedViewModel(
            getWeatherUseCase = getWeatherUseCase
        )
    }

    val locationSharedViewModel: LocationSharedViewModel by lazy {
        LocationSharedViewModel(
            searchLocationUseCase = searchLocationUseCase,
            saveLocationUseCase = saveLocationUseCase,
            getLocationUseCase = getLocationUseCase,
        )
    }
}