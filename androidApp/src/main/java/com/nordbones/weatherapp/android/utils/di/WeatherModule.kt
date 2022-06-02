package com.nordbones.weatherapp.android.utils.di

import android.app.Application
import android.content.Context
import com.nordbones.weatherapp.data.localsource.PreferencesStorage
import com.nordbones.weatherapp.data.remotesource.KtorClient
import com.nordbones.weatherapp.data.repository.LocationRepositoryImpl
import com.nordbones.weatherapp.data.repository.WeatherRepositoryImpl
import com.nordbones.weatherapp.domain.repository.LocationRepository
import com.nordbones.weatherapp.domain.repository.WeatherRepository
import com.nordbones.weatherapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Singleton
    @Provides
    fun providesGetLocationUseCase(locationRepository: LocationRepository): GetLocationUseCase =
        GetLocationUseCaseImpl(locationRepository = locationRepository)

    @Singleton
    @Provides
    fun provideGetWeatherUseCase(
        weatherRepository: WeatherRepository,
        locationRepository: LocationRepository
    ): GetWeatherUseCase = GetWeatherUseCaseImpl(
        weatherRepository = weatherRepository,
        locationRepository = locationRepository
    )

    @Singleton
    @Provides
    fun provideSearchLocationUseCase(locationRepository: LocationRepository): SearchLocationUseCase =
        SearchLocationUseCaseImpl(
            repository = locationRepository
        )

    @Singleton
    @Provides
    fun provideSaveLocationUseCase(locationRepository: LocationRepository): SaveLocationUseCase =
        SaveLocationUseCaseImpl(locationRepository = locationRepository)

    @Singleton
    @Provides
    fun provideLocationRepository(
        client: KtorClient,
        preferencesStorage: PreferencesStorage
    ): LocationRepository =
        LocationRepositoryImpl(
            client = client,
            preferencesStorage = preferencesStorage
        )

    @Singleton
    @Provides
    fun provideWeatherRepository(
        client: KtorClient,
        preferencesStorage: PreferencesStorage
    ): WeatherRepository =
        WeatherRepositoryImpl(
            client = client,
            preferencesStorage = preferencesStorage
        )

    @Singleton
    @Provides
    fun provideKtorClient(): KtorClient = KtorClient()


    @Singleton
    @Provides
    fun providePreferencesStorage(@ApplicationContext context: Context): PreferencesStorage =
        PreferencesStorage(context as Application)
}