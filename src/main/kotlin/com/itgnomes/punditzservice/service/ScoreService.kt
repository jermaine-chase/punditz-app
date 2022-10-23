package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.*
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.ScoreUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import com.itgnomes.punditzservice.util.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types
import kotlin.math.abs

@Service
class ScoreService(@Autowired private val punditzRepository : PunditzRepository,
                   @Autowired private val footBallApiService: FootBallApiService,
                   @Autowired private val pickService: PickService) {

    // create
    fun insert(p: PunditScore) {
        val jsonData = PunditzUtil.toJson(p)
        punditzRepository.save(Punditz(null, Types.SCORE.type, jsonData))
    }

    // gets
    fun getAll() : List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByType(Types.SCORE.name)
        return ScoreUtil.parseScores(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByUser(userName: String): List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.SCORE.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return ScoreUtil.parseScores(picksList)
    }

    fun getByUserAndCycleNumber(userName: String, cycleNumber: Int): List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.PREDICTIONS.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return ScoreUtil.parseScores(picksList)
    }

    // update
    fun update(score: PunditScore) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.SCORE.name, score.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        p.jsonData = PunditzUtil.toJson(score)
        punditzRepository.save(p)
    }

    // delete
    fun delete(score: PunditScore) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.SCORE.name, score.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        punditzRepository.delete(p)
    }

    fun calculateMatchScores(matchId: Int, cycleNumber: Int) {
        // get match picks
        val matchPicks = pickService.getByMatchId(matchId)

        // get match
        val match = footBallApiService.getMatch(matchId)

        matchPicks.forEach{
            // calculate score
            val score = calculateScore(match, it)

            // persist
            insert(score)
        }
    }

    fun calculateScore(match: Match, pick: Pick): PunditScore {
        var pointsWon = 0
        val results = getFinalScore(match.score)
        val scoreDifference = abs(results.awayTeam - results.homeTeam)
        var multiplier = 1
        if (scoreDifference >= 3) multiplier = 2
        if (pick.points == null) {
            pointsWon = -15
        } else {
            val winner = getWinningTeam(match)
            // DRAW
            if (winner == null) {
                pointsWon = pick.points / 2

            // WINNER
            } else if (winner.tla == pick.pick) {
                pointsWon = pick.points * multiplier
            // LOSER
            } else {
                pointsWon = 0 - pick.points
            }
        }
        return PunditScore(match.id, pointsWon, match.matchday, pick.userName)
    }

    fun getWinningTeam(match: Match): Team? {
        return if (Result.DRAW.type == match.winner) {
            null
        } else if (Result.HOME.type == match.winner) {
            match.homeTeam
        } else {
            match.awayTeam
        }
    }

    fun getFinalScore(score: Score): Time {
        // full time
        return if (score.duration == "REGULAR") {
            score.fullTime
        // over time
        } else {
            score.extraTime
        }
    }
}