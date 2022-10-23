package com.itgnomes.punditzservice.model

import java.util.Calendar

data class Match(
    val id: Int,
    val utcDate: Calendar,
    val status: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val winner: String,
    val matchday: Int,
    val season: Season?
)
