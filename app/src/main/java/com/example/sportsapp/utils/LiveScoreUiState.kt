package com.example.sportsapp.utils

import com.example.sportsapp.domain.network.model.Result

sealed class LiveScoreUiState {
    data class Success(val list: List<Result>): LiveScoreUiState()
    data class Loading(val isLoading: Boolean): LiveScoreUiState()
    data class Error(val exception: Throwable): LiveScoreUiState()
}