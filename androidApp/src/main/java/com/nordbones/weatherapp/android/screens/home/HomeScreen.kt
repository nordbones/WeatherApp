package com.nordbones.weatherapp.android.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nordbones.weatherapp.android.R
import com.nordbones.weatherapp.android.navigation.NavigationTree
import com.nordbones.weatherapp.android.screens.home.widgets.Card
import com.nordbones.weatherapp.android.screens.home.widgets.MainWeatherWidget
import com.nordbones.weatherapp.android.utils.Handle
import com.nordbones.weatherapp.domain.model.Weather
import com.nordbones.weatherapp.viewmodel.ViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val weatherState by viewModel.weatherState.collectAsState(initial = ViewState(isLoading = true))

    LaunchedEffect(key1 = Unit) {
        viewModel.getWeather()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { navController.navigate(route = NavigationTree.SETTINGS.navTarget) }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                Text(text = stringResource(R.string.settings))
            }
        }) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = false),
            onRefresh = { viewModel.getWeather() }) {
            HomeScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = paddingValues.calculateTopPadding()
                    ),
                state = weatherState,
            )
        }
    }
}


@Composable
fun HomeScreenContent(
    modifier: Modifier,
    state: ViewState<Weather>,
) {
    val scrollState = rememberScrollState()
    state.Handle { weather ->
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.dp20))
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center
        ) {
            MainWeatherWidget(weather = weather)

            Row {
                Card(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.pressure),
                    value = weather.airPressure.toString(),
                    units = stringResource(R.string.millibars)
                )
                Card(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.humidity),
                    value = weather.humidityInPercents.toString(),
                    units = stringResource(R.string.percents)
                )
            }
            Row {
                Card(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.visibility),
                    value = weather.visibilityInMiles.toString(),
                    units = stringResource(R.string.miles)
                )
                Card(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.wind),
                    value = weather.windSpeedInMpH.toString(),
                    units = stringResource(R.string.mph)
                )
            }
        }
    }
}

