package com.nordbones.weatherapp.android.navigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.nordbones.weatherapp.android.screens.home.HomeScreen
import com.nordbones.weatherapp.android.screens.settings.SettingsScreen

@OptIn(ExperimentalAnimationApi::class)
enum class NavigationTree constructor(
    val navTarget: String,
    val content: @Composable (NavController, Bundle?) -> Unit,
    private val tweenSpec: TweenSpec<IntOffset> = tween(
        durationMillis = 500,
        easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
    ),
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        if (initialState.destination.route != targetState.destination.route) {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tweenSpec
            )
        } else null
    },
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        if (targetState.destination.route != initialState.destination.route) {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tweenSpec
            )
        } else null
    },
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        if (initialState.destination.route != targetState.destination.route) {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tweenSpec
            )
        } else null
    },
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        if (targetState.destination.route != initialState.destination.route) {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tweenSpec
            )
        } else null
    },
) {
    HOME(
        navTarget = "home",
        content = { navController, _ ->
            HomeScreen(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        },
    ),
    SETTINGS(
        navTarget = "settings",
        content = { navController, _ ->
            SettingsScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        },
    );
}