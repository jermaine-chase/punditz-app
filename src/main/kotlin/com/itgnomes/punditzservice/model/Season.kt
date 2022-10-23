package com.itgnomes.punditzservice.model

import java.time.LocalDate

data class Season(
    val id: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currentMatchday: Int
)