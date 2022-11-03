package com.itgnomes.punditzservice.model

data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int
)