package com.example.sportsapp.domain.network.model

data class LiveScore(
    val result: List<Result>,
    val success: Int
)