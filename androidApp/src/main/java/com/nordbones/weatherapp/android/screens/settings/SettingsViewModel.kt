package com.nordbones.weatherapp.android.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nordbones.weatherapp.android.utils.TextFieldState
import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.viewmodel.LocationSharedViewModel
import com.nordbones.weatherapp.viewmodel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun onLocationChange(value: String) {
        viewModelScope.launch {
            mLocationFieldState.emit(
                value = TextFieldState(text = value, error = value.length < 3)
            )
        }
    }

    fun searchLocation() {
        if (!mLocationFieldState.value.error) {
            locationSharedViewModel.searchLocation(location = mLocationFieldState.value.text)
                .collectCommon { state ->
                    viewModelScope.launch {
                        mLocationState.emit(state)
                    }
                }
        }
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            locationSharedViewModel.saveLocation(location = location)
        }
    }

}