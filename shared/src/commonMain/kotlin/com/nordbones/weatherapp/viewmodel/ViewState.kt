package com.nordbones.weatherapp.viewmodel

data class ViewState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: Throwable? = null
)