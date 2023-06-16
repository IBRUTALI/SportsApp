package com.example.sportsapp.domain.network.model

data class Substitute(
    val info_time: String,
    val player: String,
    val player_country: Any,
    val player_key: Long,
    val player_number: Int,
    val player_position: Int
)