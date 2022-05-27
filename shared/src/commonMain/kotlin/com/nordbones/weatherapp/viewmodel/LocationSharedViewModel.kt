package com.nordbones.weatherapp.viewmodel

import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.domain.usecase.GetLocationUseCase
import com.nordbones.weatherapp.domain.usecase.SaveLocationUseCase
import com.nordbones.weatherapp.domain.usecase.SearchLocationUseCase
import com.nordbones.weatherapp.utils.CommonFlow
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.asCommonFlow
import com.nordbones.weatherapp.viewmodel.ViewState
import kotlinx.coroutines.flow.flow

class LocationSharedViewModel(
    private val searchLocationUseCase: SearchLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getLocationUseCase: GetLocationUseCase
) {
    fun searchLocation(location: String): CommonFlow<ViewState<Locations>> = flow {
        emit(ViewState<Locations>(isLoading = true))
        searchLocationUseCase.invoke(location = location).collect { result ->
            when (result) {
                is ResultWrapper.Error -> emit(ViewState<Locations>(error = result.exception))
                ResultWrapper.Loading -> emit(ViewState<Locations>(isLoading = true))
                is ResultWrapper.Success -> emit(ViewState<Locations>(data = result.data))
            }
        }
    }.asCommonFlow()

    suspend fun saveLocation(location: Location) {
        saveLocationUseCase.invoke(location = location)
    }

    suspend fun getLocation(): Location = getLocationUseCase()
}