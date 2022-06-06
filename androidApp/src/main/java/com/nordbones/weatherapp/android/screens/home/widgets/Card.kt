package com.nordbones.weatherapp.android.screens.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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


@Composable
fun Card(modifier: Modifier, label: String, value: String, units: String) {
    Surface(
        modifier = modifier.padding(dimensionResource(id = R.dimen.dp5)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp10)),
        shadowElevation = dimensionResource(id = R.dimen.dp5),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Column(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dp20)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label)
            Text(text = value)
            Text(text = units)
        }
    }
}