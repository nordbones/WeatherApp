package com.nordbones.weatherapp.android.utils

import com.nordbones.weatherapp.utils.EMPTY


data class TextFieldState(val text: String, val error: Boolean) {
    companion object {
        val empty = TextFieldState(text = String.EMPTY, error = false)
    }
}