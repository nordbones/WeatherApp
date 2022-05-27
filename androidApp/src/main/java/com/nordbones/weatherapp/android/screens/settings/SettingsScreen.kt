package com.nordbones.weatherapp.android.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nordbones.weatherapp.android.utils.TextFieldState
import com.nordbones.weatherapp.viewmodel.ViewState


@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val locationFieldState by viewModel.locationFieldState.collectAsState(TextFieldState.empty)
    val locationState by viewModel.locationState.collectAsState(initial = ViewState())

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = locationFieldState.text,
            onValueChange = viewModel::onLocationChange,
            placeholder = { Text("Location") },
            isError = locationFieldState.error,
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = viewModel::searchLocation) {
            Text(text = "Search")
        }

        when {
            locationState.isLoading -> CircularProgressIndicator()
            locationState.data != null -> locationState.data?.let { locations ->
                LazyColumn {
                    locations.list.forEach { location ->
                        item {
                            Text(
                                text = "${location.name}, ${location.region}, ${location.country}",
                                modifier = Modifier.clickable {
                                    viewModel.saveLocation(location)
                                })
                        }
                    }
                }
            }
            locationState.error != null -> Text("Error: ${locationState.error?.message}")
        }
    }
}