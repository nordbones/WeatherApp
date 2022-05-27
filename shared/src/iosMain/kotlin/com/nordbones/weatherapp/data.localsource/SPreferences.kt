package com.nordbones.weatherapp.data.localsource

import com.nordbones.weatherapp.utils.EMPTY
import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SPreferences = NSObject

actual fun SPreferences.setFloatValue(key: String, value: Float) {
    NSUserDefaults.standardUserDefaults.setFloat(value, key)
}

actual fun SPreferences.getFloatValue(key: String): Float {
    return NSUserDefaults.standardUserDefaults.floatForKey(key)
}

actual fun SPreferences.setStringValue(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun SPreferences.getStringValue(key: String): String {
    return NSUserDefaults.standardUserDefaults.stringForKey(key) ?: String.EMPTY
}