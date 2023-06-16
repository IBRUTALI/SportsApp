package com.example.sportsapp.utils

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object SplashScreen: Screen("splash_screen")
    object WebScreen: Screen("web_screen")
}
