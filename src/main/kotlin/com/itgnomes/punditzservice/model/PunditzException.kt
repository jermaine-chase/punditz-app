package com.itgnomes.punditzservice.model

import java.time.LocalDate

data class PunditzException(val cycleNumber: Int, val gameMap: Map<Int, LocalDate>, val leagueId: String, val season: Int)