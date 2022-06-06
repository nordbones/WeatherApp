package com.nordbones.weatherapp.android.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.nordbones.weatherapp.android.R
import com.nordbones.weatherapp.android.utils.Handle
import com.nordbones.weatherapp.android.utils.TextFieldState
import com.nordbones.weatherapp.domain.model.Location
import com.nordbones.weatherapp.domain.model.Locations
import com.nordbones.weatherapp.utils.EMPTY
import com.nordbones.weatherapp.viewmodel.ViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel, navController: NavController) {
    val locationFieldState by viewModel.locationFieldState.collectAsState(TextFieldState.empty)
    val locationState by viewModel.locationState.collectAsState(initial = ViewState())


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.settings)) },
                navigationIcon = {
                    ElevatedButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        SettingsScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            locationState = locationState,
            locationFieldState = locationFieldState,
            onChangeLocationFiled = viewModel::onLocationChange,
            onClearLocationFiled = viewModel::onLocationClear,
            saveLocation = viewModel::saveLocation
        )
    }
}

@Composable
fun SettingsScreenContent(
    modifier: Modifier,
    locationState: ViewState<Locations>,
    locationFieldState: TextFieldState,
    onChangeLocationFiled: (String) -> Unit,
    onClearLocationFiled: () -> Unit,
    saveLocation: (Location) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.dp10))) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.location)) },
            value = locationFieldState.text,
            onValueChange = onChangeLocationFiled,
            isError = locationFieldState.error,
            trailingIcon = {
                IconButton(onClick = onClearLocationFiled) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        )

        locationState.Handle() { locations ->
            LazyColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dp20))) {
                locations.list.forEach { location ->
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { saveLocation(location) }
                                .padding(vertical = dimensionResource(id = R.dimen.dp10)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = location.fullTitle)
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}


val Location.fullTitle
    get() = if (name.isNotBlank() && region.isNotBlank() && country.isNotBlank()) "$name,$region,$country" else String.EMPTY