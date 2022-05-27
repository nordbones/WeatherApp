package com.nordbones.weatherapp.android.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nordbones.weatherapp.viewmodel.ViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val weatherState by viewModel.weatherState.collectAsState(initial = ViewState())


    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            Button(onClick = viewModel::getWeather) {
                Text(text = "Get Weather")
            }

            when {
                weatherState.isLoading -> CircularProgressIndicator()
                weatherState.data != null -> weatherState.data?.let { weather ->
                    Text(text = weather.toString())
                }
                weatherState.error != null -> Text("Error: ${weatherState.error?.message}")
            }
        }
    }
}
