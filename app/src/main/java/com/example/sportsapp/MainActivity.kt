package com.example.sportsapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportsapp.presentation.main.MainViewModel
import com.example.sportsapp.presentation.main.MainViewModelFactory
import com.example.sportsapp.ui.Navigation
import com.example.sportsapp.ui.theme.SportsAppTheme

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
            SportsAppTheme {
                Navigation(
                    list = list,
                    isLoading = isLoading
                )
            }
        }
    }
}