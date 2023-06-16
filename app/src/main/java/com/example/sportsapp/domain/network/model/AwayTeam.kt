package com.example.sportsapp.domain.network.model

data class AwayTeam(
    val coaches: List<Coache>,
    val missing_players: List<Any>,
    val starting_lineups: List<StartingLineup>,
    val substitutes: List<Substitute>
)