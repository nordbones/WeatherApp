package com.nordbones.weatherapp.viewmodel

import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.domain.usecase.GetWeatherUseCase
import com.nordbones.weatherapp.utils.CommonFlow
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.asCommonFlow
import com.nordbones.weatherapp.viewmodel.ViewState
import kotlinx.coroutines.flow.flow

class WeatherSharedViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
) {
    fun getWeather(): CommonFlow<ViewState<Weather>> = flow {
        emit(ViewState(isLoading = true))
        getWeatherUseCase.invoke().collect { result ->
            when (result) {
                is ResultWrapper.Error -> emit(ViewState<Weather>(error = result.exception))
                ResultWrapper.Loading -> emit(ViewState<Weather>(isLoading = true))
                is ResultWrapper.Success -> emit(ViewState(data = result.data))
            }
        }
    }.asCommonFlow()
}

