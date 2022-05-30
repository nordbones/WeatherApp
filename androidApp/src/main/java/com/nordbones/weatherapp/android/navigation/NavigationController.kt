package com.nordbones.weatherapp.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationController(
    startDestination: String,
    screens: Array<NavigationTree>
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = startDestination) {
        screens.forEach { screen ->
            composable(
                route = screen.navTarget,
                enterTransition = screen.enterTransition,
                exitTransition = screen.exitTransition,
                popEnterTransition = screen.popEnterTransition,
                popExitTransition = screen.popExitTransition
            ) {
                screen.content.invoke(
                    navController,
                    navController.previousBackStackEntry?.arguments
                )
            }
        }
    }
}