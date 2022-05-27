package com.nordbones.weatherapp.android.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.viewmodel.ViewState
import com.nordbones.weatherapp.viewmodel.WeatherSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherSharedViewModel: WeatherSharedViewModel) :
    ViewModel() {

    init {
        getWeather()
    }

    private val mWeatherState: MutableSharedFlow<ViewState<Weather>> = MutableSharedFlow()
    val weatherState: SharedFlow<ViewState<Weather>> = mWeatherState


    fun getWeather() {
        weatherSharedViewModel.getWeather().collectCommon { state ->
            viewModelScope.launch {
                mWeatherState.emit(state)
            }
        }
    }
}