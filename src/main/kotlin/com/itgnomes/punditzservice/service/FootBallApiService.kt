package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.model.Match
import com.itgnomes.punditzservice.model.Team
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.stereotype.Service

@Service
class FootBallApiService {
    fun getMatches(leagueId: Int = 2021): List<Match> {
        val url = ApiService.BASEURL+leagueId+ApiService.MATCHES
        val resp = ApiService.invoke(url)
        return PunditzUtil.parseMatches(resp)
    }

    fun getTeam(leagueId: Int = 2021): List<Team> {
        val url = ApiService.BASEURL+leagueId+ApiService.TEAMS
        val resp = ApiService.invoke(url)
        return PunditzUtil.parseTeams(resp)
    }
}