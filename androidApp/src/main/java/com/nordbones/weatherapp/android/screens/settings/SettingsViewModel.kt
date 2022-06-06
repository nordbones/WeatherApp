package com.nordbones.weatherapp.android.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nordbones.weatherapp.android.utils.TextFieldState
import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.utils.CommonFlow
import com.nordbones.weatherapp.utils.EMPTY
import com.nordbones.weatherapp.viewmodel.LocationSharedViewModel
import com.nordbones.weatherapp.viewmodel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val locationSharedViewModel: LocationSharedViewModel) :
    ViewModel() {

    private val mLocationFieldState: MutableStateFlow<TextFieldState> =
        MutableStateFlow(TextFieldState.empty)
    val locationFieldState: StateFlow<TextFieldState> = mLocationFieldState

    private val mLocationState: MutableSharedFlow<ViewState<Locations>> = MutableSharedFlow()
    val locationState: SharedFlow<ViewState<Locations>> = mLocationState

    init {
        getLocation()
    }

    fun onLocationChange(value: String) {
        viewModelScope.launch {
            val isNotValid = value.length < 5
            mLocationFieldState.emit(
                value = TextFieldState(text = value, error = isNotValid)
            )
            if (!isNotValid) {
                locationSharedViewModel.searchLocation(value)
                    .collectInScope(viewModelScope) {
                        mLocationState.emit(it)
                    }
            }
        }
    }

    fun onLocationClear() {
        viewModelScope.launch {
            mLocationFieldState.emit(
                value = TextFieldState(text = String.EMPTY, error = false)
            )
        }
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            locationSharedViewModel.saveLocation(location = location)
            mLocationState.emit(ViewState())
            mLocationFieldState.emit(TextFieldState(text = location.fullTitle, error = false))
        }
    }

    private fun getLocation() {
        viewModelScope.launch {
            locationSharedViewModel.getLocation().apply {
                mLocationFieldState.emit(
                    TextFieldState(text = fullTitle, error = false)
                )
            }
        }
    }
}

fun <T> CommonFlow<T>.collectInScope(scope: CoroutineScope, callback: suspend (T) -> Unit) {
    onEach {
        callback(it)
    }.launchIn(scope)
}