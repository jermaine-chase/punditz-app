package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.model.Match
import com.itgnomes.punditzservice.service.FootBallApiService
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired

@RestController
class MatchController(@Autowired private val footBallApiService: FootBallApiService) {

    @GetMapping("/match/byCycle/{cycleNumber}")
    fun get(@PathVariable cycleNumber: Int): List<Match> {
        return footBallApiService.getMatchByCycle(cycleNumber)
    }

    @GetMapping("/match/id/{matchId}")
    fun getOne(@PathVariable matchId: Int): Match {
        return footBallApiService.getMatch(matchId)
    }

    @GetMapping("/match")
    fun getAll(): List<Match> {
        return footBallApiService.getMatches(PunditzUtil.EPL)
    }
}