package com.nordbones.weatherapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}