package com.itgnomes.punditzservice.model

import java.time.LocalDate

data class Cycle(val cycleNumber: Int, val matchMap: Map<Int, Int>, var exception: Exception, val startDate: LocalDate, val endDate: LocalDate, val lastUpdate: LocalDate, val leagueId: String, val season: Int)