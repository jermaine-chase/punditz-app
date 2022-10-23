package com.itgnomes.punditzservice.model

import java.time.LocalDate

data class Cycle(
    val cycleNumber: Int,
    val matchList: MutableList<Int>,
    var exception: Exception?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val leagueId: Int,
    val seasonId: Int?
)