package com.nordbones.weatherapp.android.utils.di

import com.nordbones.weatherapp.domain.usecase.GetLocationUseCase
import com.nordbones.weatherapp.domain.usecase.GetWeatherUseCase
import com.nordbones.weatherapp.domain.usecase.SaveLocationUseCase
import com.nordbones.weatherapp.domain.usecase.SearchLocationUseCase
import com.nordbones.weatherapp.viewmodel.LocationSharedViewModel
import com.nordbones.weatherapp.viewmodel.WeatherSharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SharedViewModelModule {
    @Provides
    fun provideSharedWeatherViewModel(
        getWeatherUseCase: GetWeatherUseCase,
    ): WeatherSharedViewModel =
        WeatherSharedViewModel(
            getWeatherUseCase = getWeatherUseCase,

            )

    @Provides
    fun provideLocationSharedViewModel(
        searchLocationUseCase: SearchLocationUseCase,
        saveLocationUseCase: SaveLocationUseCase,
        getLocationUseCase: GetLocationUseCase
    ): LocationSharedViewModel =
        LocationSharedViewModel(
            searchLocationUseCase = searchLocationUseCase,
            saveLocationUseCase = saveLocationUseCase,
            getLocationUseCase = getLocationUseCase
        )
}