package com.nordbones.weatherapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nordbones.kmmweather.android.ui.theme.AppTheme
import com.nordbones.weatherapp.android.navigation.NavigationController
import com.nordbones.weatherapp.android.navigation.NavigationTree
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NavigationController(
                    startDestination = NavigationTree.HOME.navTarget,
                    screens = NavigationTree.values()
                )
            }
        }
    }
}