package com.itgnomes.punditzservice.model

data class Score(
    val winner: String,
    val duration: String,
    val fullTime: Time,
    val halfTime: Time,
    val extraTime: Time,
    val penalties: Time
)
