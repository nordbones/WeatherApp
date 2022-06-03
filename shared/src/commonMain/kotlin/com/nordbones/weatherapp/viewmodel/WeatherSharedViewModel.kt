package com.nordbones.weatherapp.viewmodel

import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.domain.usecase.GetWeatherUseCase
import com.nordbones.weatherapp.utils.CommonFlow
import com.nordbones.weatherapp.utils.ResultWrapper
import com.nordbones.weatherapp.utils.asCommonFlow
import kotlinx.coroutines.flow.flow

class WeatherSharedViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
) {
    fun getWeather(): CommonFlow<ViewState<Weather>> = flow {
        getWeatherUseCase.invoke().collect { result ->
            when (result) {
                ResultWrapper.Loading -> emit(ViewState<Weather>(isLoading = true))
                is ResultWrapper.Success -> emit(ViewState(data = result.data))
                is ResultWrapper.Error -> emit(ViewState<Weather>(error = result.exception, data = result.defaultData))
            }
        }
    }.asCommonFlow()
}

