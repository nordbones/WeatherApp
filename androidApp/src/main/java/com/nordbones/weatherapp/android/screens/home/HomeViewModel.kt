package com.nordbones.weatherapp.android.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.viewmodel.ViewState
import com.nordbones.weatherapp.viewmodel.WeatherSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherSharedViewModel: WeatherSharedViewModel) :
    ViewModel() {

    private val mWeatherState: MutableStateFlow<ViewState<Weather>> = MutableStateFlow(ViewState(isLoading = true))
    val weatherState: StateFlow<ViewState<Weather>> = mWeatherState


    fun getWeather() {
        weatherSharedViewModel.getWeather().collectCommon { state ->
            viewModelScope.launch {
                mWeatherState.emit(state)
            }
        }
    }
}