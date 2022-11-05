package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.model.Match
import com.itgnomes.punditzservice.model.Team
import com.itgnomes.punditzservice.util.CycleUtil
import com.itgnomes.punditzservice.util.Logs
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit
import java.util.*

@Service
class FootBallApiService {
    companion object {
        lateinit var MATCHES: MutableList<Match>
        lateinit var MATCH_TIMESTAMP: Instant

        lateinit var TEAMS: MutableList<Team>
        lateinit var TEAM_TIMESTAMP: Instant

        lateinit var CYCLES: MutableList<Cycle>
        lateinit var CYCLE_TIMESTAMP: Instant

        fun getTime(dateOffset: Long): Instant {
            val now = Instant.now()
            return now.plus(dateOffset, ChronoUnit.HOURS)
        }
    }

    init {
        Logs.info(FootBallApiService::class, "Initializing caches")
        MATCHES = mutableListOf<Match>()
        TEAMS = mutableListOf<Team>()
        CYCLES = mutableListOf<Cycle>()
        MATCH_TIMESTAMP = getTime(-10)
        TEAM_TIMESTAMP = getTime(-10)
        CYCLE_TIMESTAMP = getTime(-10)
    }

    fun getMatches(leagueId: Int? = 2021): List<Match> {
        Logs.info(FootBallApiService::class, "Getting Matches for $leagueId")
        val now = getTime(-1)
        if (MATCHES.isEmpty() || now.isAfter(MATCH_TIMESTAMP)) {
            Logs.info(FootBallApiService::class, "Match Cache is empty/expired. Populating")
            val url = ApiService.BASEURL + leagueId + ApiService.MATCHES
            val resp = ApiService.invoke(url)
            MATCHES = PunditzUtil.parseMatches(resp)
            val teamsMap = getTeams(leagueId).associateBy { it.id }
            MATCHES.forEach {
                it.homeTeam = teamsMap[it.homeTeam.id]!!
                it.awayTeam = teamsMap[it.awayTeam.id]!!
            }
            MATCH_TIMESTAMP = Instant.now()
            Logs.info(FootBallApiService::class, "Catch refreshed @ $MATCH_TIMESTAMP")
        }
        return MATCHES
    }

    fun getMatch(matchId: Int): Match {
        val match = getMatches().filter{it.id == matchId}
        Logs.info(FootBallApiService::class, "Getting Match $matchId; size of filtered result is ${match.size}")
        return match[0]
    }

    fun getTeams(leagueId: Int? = 2021): List<Team> {
        Logs.info(FootBallApiService::class, "Getting Teams for $leagueId")
        val now = getTime(-1)
        if (TEAMS.isEmpty() || now.isAfter(TEAM_TIMESTAMP)) {
            Logs.info(FootBallApiService::class, "Teams Cache is empty/expired. Populating")
            val url = ApiService.BASEURL + leagueId + ApiService.TEAMS
            val resp = ApiService.invoke(url)
            TEAMS = PunditzUtil.parseTeams(resp)
            TEAM_TIMESTAMP = Instant.now()
            Logs.info(FootBallApiService::class, "Catch refreshed @ $TEAM_TIMESTAMP")
        }
        return TEAMS
    }

    fun getTeam(teamId: Int?, teamName: String?): Team {
        val team = getTeams().filter{it.id == teamId || it.name == teamName}
        return team[0]
    }

    fun getCycles(leagueId: Int = 2021): MutableMap<Int, Cycle> {
        val now = getTime(-1)
        val cycleMap: MutableMap<Int, Cycle>
        if (CYCLES.isEmpty() || now.isAfter(CYCLE_TIMESTAMP)) {
            val matches = getMatches(leagueId)
            cycleMap = CycleUtil.calculateCycles(matches, leagueId)
            CYCLES = cycleMap.values.toMutableList()
            CYCLE_TIMESTAMP = Instant.now()
        } else {
            cycleMap = CycleUtil.calculateCycles(MATCHES, leagueId)
        }
        return cycleMap
    }

    fun getCycleList(leagueId: Int): List<Cycle> {
        if (CYCLES.isEmpty()) {
            getCycles(leagueId)
        }
        return CYCLES
    }

    fun getCycle(cycleNumber: Int): Cycle? {
        return getCycles()[cycleNumber]
    }

    fun getMatchByCycle(cycleNumber: Int): List<Match> {
        val matchList = mutableListOf<Match>()
        val cycle = getCycle(cycleNumber)

        cycle?.matchList?.forEach { matchList.add(getMatch(it)) }
        return matchList
    }
}