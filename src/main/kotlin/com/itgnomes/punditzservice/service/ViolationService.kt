package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.PunditScore
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.PunditzUtil
import com.itgnomes.punditzservice.util.ScoreUtil
import com.itgnomes.punditzservice.util.Types
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ViolationService(@Autowired private val punditzRepository : PunditzRepository) {
    fun insert(p: PunditScore) {
        val jsonData = PunditzUtil.toJson(p).toString()
        punditzRepository.save(Punditz(null, Types.VIOLATION.type, jsonData))
    }

    // gets
    fun getAll() : List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByType(Types.VIOLATION.name)
        return ScoreUtil.parseScores(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByUser(userName: String): List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.VIOLATION.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return ScoreUtil.parseScores(picksList)
    }

    fun getByUserAndCycleNumber(userName: String, cycleNumber: Int): List<PunditScore> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.VIOLATION.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return ScoreUtil.parseScores(picksList)
    }

    // update
    fun update(score: PunditScore) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.VIOLATION.name, score.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        p.jsonData = PunditzUtil.toJson(score).toString()
        punditzRepository.save(p)
    }

    // delete
    fun delete(score: PunditScore) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.VIOLATION.name, score.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        punditzRepository.delete(p)
    }
}