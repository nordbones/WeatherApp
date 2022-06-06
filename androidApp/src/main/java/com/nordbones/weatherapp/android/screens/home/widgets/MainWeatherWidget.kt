package com.nordbones.weatherapp.android.screens.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nordbones.weatherapp.android.R
import com.nordbones.weatherapp.domain.model.Weather


@Composable
fun MainWeatherWidget(weather: Weather) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = dimensionResource(id = R.dimen.dp5),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.dp10)
        ),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.dp20)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "${weather.name},${weather.country}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = weather.description,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = weather.temp.toString(),
                style = MaterialTheme.typography.displayLarge
            )

        }
    }
}