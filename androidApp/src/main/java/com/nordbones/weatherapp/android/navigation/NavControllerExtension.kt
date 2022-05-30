package com.nordbones.weatherapp.android.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigate(
    route: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}

fun NavController.navigate(
    route: String,
    param: Pair<String, Parcelable>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second) }
    navigate(route, builder)
}