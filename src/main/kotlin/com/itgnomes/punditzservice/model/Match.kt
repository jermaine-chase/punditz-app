package com.itgnomes.punditzservice.model

data class Match(
    val id: Int,
    val utcDate: String,
    val status: String,
    var homeTeam: Team,
    var awayTeam: Team,
    val score: Score,
    val winner: String,
    val matchday: Int,
    val season: Season?
)
