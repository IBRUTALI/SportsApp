package com.example.sportsapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sportsapp.presentation.main.MainViewModel
import com.example.sportsapp.presentation.main.MainViewModelFactory
import com.example.sportsapp.ui.Navigation
import com.example.sportsapp.ui.screens.WebViewAction
import com.example.sportsapp.ui.theme.SportsAppTheme
import com.example.sportsapp.utils.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = viewModel(
                this,
                "MainViewModel",
                MainViewModelFactory(LocalContext.current.applicationContext as Application)
            )
            mainViewModel.getAllCache()
            val list = mainViewModel.resultList.observeAsState()
            val isLoading = mainViewModel.isLoading.collectAsState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val isSplashScreen = rememberSaveable { mutableStateOf(true)}
            when(navBackStackEntry?.destination?.route) {
                Screen.SplashScreen.route -> isSplashScreen.value = true
                else -> isSplashScreen.value = false
            }
            SportsAppTheme {
                Scaffold(
                    topBar = {
                        if (!isSplashScreen.value) {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = stringResource(id = R.string.app_name),
                                        style = MaterialTheme.typography.h2
                                    )
                                },
                                actions = { WebViewAction(navController) }
                            )
                        }
                    },
                    content = {
                        Modifier.padding(paddingValues = it)
                        Navigation(
                            list = list,
                            isLoading = isLoading,
                            onRefresh = { mainViewModel.insertInDBFromNetwork() },
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}