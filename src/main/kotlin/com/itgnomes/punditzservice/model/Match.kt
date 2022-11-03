package com.itgnomes.punditzservice.model

data class Match(
    val id: Int,
    val utcDate: String,
    val status: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val winner: String,
    val matchday: Int,
    val season: Season?
)
