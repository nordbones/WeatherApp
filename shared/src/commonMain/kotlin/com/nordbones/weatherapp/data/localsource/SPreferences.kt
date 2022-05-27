package com.nordbones.weatherapp.data.localsource

expect class SPreferences

//expect fun SPreferences.saveLocation(latitude: Double, longitude: Double)
//expect fun SPreferences.getLatitude():Double
//expect fun SPreferences.getLongitude():Double

expect fun SPreferences.setFloatValue(key:String, value:Float)

expect fun SPreferences.getFloatValue(key: String):Float

expect fun SPreferences.setStringValue(key:String, value:String)

expect fun SPreferences.getStringValue(key: String):String