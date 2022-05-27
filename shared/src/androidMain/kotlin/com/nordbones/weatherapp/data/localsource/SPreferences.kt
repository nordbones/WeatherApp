package com.nordbones.weatherapp.data.localsource

import android.app.Application
import android.content.SharedPreferences
import com.nordbones.weatherapp.utils.EMPTY

actual typealias SPreferences = Application

private const val SHARED_PREF = "shared_pref"

val Application.preferences: SharedPreferences
    get() = this.getSharedPreferences(SHARED_PREF, SPreferences.MODE_PRIVATE)

actual fun SPreferences.setFloatValue(key: String, value: Float) {
    with(preferences.edit()) {
        putFloat(key, value)
        apply()
    }
}

actual fun SPreferences.getFloatValue(key: String): Float = preferences.getFloat(key, -1f)

actual fun SPreferences.setStringValue(key: String, value: String) {
    with(preferences.edit()) {
        putString(key, value)
        apply()
    }
}

actual fun SPreferences.getStringValue(key: String): String =
    preferences.getString(key, String.EMPTY) ?: String.EMPTY