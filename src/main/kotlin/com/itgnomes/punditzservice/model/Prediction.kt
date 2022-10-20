package com.itgnomes.punditzservice.model

data class Prediction(val userName: String, val games: MutableList<Int>, val leagueId: String, val season: Int)