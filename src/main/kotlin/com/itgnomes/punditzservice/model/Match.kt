package com.itgnomes.punditzservice.model

import java.util.Calendar

data class Match(val id: Long, val matchTime: Calendar, val status: String, val homeTeam: Team, val awayTeam: Team, val score: Score, val winner: String)
