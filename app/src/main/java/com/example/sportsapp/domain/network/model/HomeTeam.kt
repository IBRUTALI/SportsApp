package com.example.sportsapp.domain.network.model

data class HomeTeam(
    val coaches: List<Any>,
    val missing_players: List<Any>,
    val starting_lineups: List<StartingLineup>,
    val substitutes: List<SubstituteXX>
)