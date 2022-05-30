package com.nordbones.weatherapp.android.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nordbones.weatherapp.viewmodel.ViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val weatherState by viewModel.weatherState.collectAsState(initial = ViewState())

    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate("settings") }) {
            Text(text = "Settings")
        }

        Text(
            "Content", modifier = Modifier
                .align(Alignment.Center)
                .size(120.dp)
                .background(Color.Cyan)
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { navController.navigate("settings") }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                Text(text = "Settings")
            }
        }) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding(),
                top = paddingValues.calculateTopPadding()
            )
        )
    }
}


@Composable
fun HomeScreenContent(modifier: Modifier) {

}