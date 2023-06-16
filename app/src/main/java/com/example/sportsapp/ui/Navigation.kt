package com.example.sportsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsapp.domain.network.model.Result
import com.example.sportsapp.ui.screens.MainScreen
import com.example.sportsapp.ui.screens.SplashScreen
import com.example.sportsapp.utils.Screen

@Composable
fun Navigation(
    list: State<List<Result>?>,
    isLoading: State<Boolean>
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController,
                isLoading
            )
        }
        composable(
            route = Screen.MainScreen.route,
        ) {
            MainScreen(
                navController = navController,
                list = list
            )
        }
    }
}