package com.nordbones.weatherapp.utils

import kotlin.math.pow
import kotlin.math.round


fun Double.fromFahrenheitToCelsius() = (this - 32) * 5 / 9

fun Double.toPercents() = this * 100

fun Double.trim(levelAccuracy: Int): Double {
    val accuracy = 10.0.pow(levelAccuracy)
    return round(this * 100) / 100
}

