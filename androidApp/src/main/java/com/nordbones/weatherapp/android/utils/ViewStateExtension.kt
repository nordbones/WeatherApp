package com.nordbones.weatherapp.android.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nordbones.weatherapp.android.R
import com.nordbones.weatherapp.viewmodel.ViewState

@Composable
fun <T> ViewState<T>.Handle(
    onLoading: @Composable () -> Unit = { DefaultLoading() },
    onError: @Composable (Throwable) -> Unit = { DefaultError(it) },
    onLoaded: @Composable (T) -> Unit
) =
    when {
        isLoading -> onLoading.invoke()
        data != null -> onLoaded.invoke(data!!)
        error != null -> onError.invoke(error!!)
        error != null && data != null -> onLoaded.invoke(data!!)
        else -> Unit
    }

@Composable
private fun DefaultLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.loading))
        CircularProgressIndicator()
    }
}


@Composable
private fun DefaultError(throwable: Throwable) {
    val countLines = 3

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Warning, contentDescription = null)
        Text(text = stringResource(R.string.error_we_re_sorry))
        Text(text = throwable.message.toString(), maxLines = countLines)
    }
}

