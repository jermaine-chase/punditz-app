package com.itgnomes.punditzservice.model

import java.time.LocalDate

data class Exception(val cycleNumber: Int, val gameMap: Map<Int, LocalDate>, val leagueId: String, val season: Int)