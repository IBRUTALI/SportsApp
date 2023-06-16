package com.example.sportsapp.domain.network.model

data class Card(
    val away_fault: String,
    val away_player_id: String,
    val card: String,
    val home_fault: String,
    val home_player_id: String,
    val info: String,
    val info_time: String,
    val time: String
)