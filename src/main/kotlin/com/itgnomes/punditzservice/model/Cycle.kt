package com.itgnomes.punditzservice.model

data class Cycle(
    val cycleNumber: Int,
    val matchList: MutableList<Int>,
    var exception: Exception?,
    var startDate: String?,
    var endDate: String?,
    val leagueId: Int,
    val seasonId: Int?
)