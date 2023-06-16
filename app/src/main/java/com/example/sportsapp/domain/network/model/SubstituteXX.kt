package com.example.sportsapp.domain.network.model

data class SubstituteXX(
    val away_assist: String,
    val away_scorer: AwayScorer,
    val home_assist: String,
    val home_scorer: HomeScorer,
    val info: String,
    val info_time: String,
    val score: String,
    val time: String
)