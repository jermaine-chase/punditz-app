package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.Pick
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.PickUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class PickService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(p: Pick) {
        val jsonData = PunditzUtil.toJson(p).toString()
        punditzRepository.save(Punditz(null, Types.PICKS.type, jsonData))
    }

    // gets
    fun getAll() : List<Pick> {
        val punditzResponse = punditzRepository.findAllByType(Types.PICKS.name)
        return PickUtil.parsePicks(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByUser(userName: String): List<Pick> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.PICKS.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return PickUtil.parsePicks(picksList)
    }

    fun getByUserAndCycleNumber(userName: String?, cycleNumber: Int): List<Pick> {
        val punditzResponse = if (userName == null) {
            punditzRepository.findAllByTypeAndCycleNumber(Types.PICKS.name, cycleNumber)
        } else {
            punditzRepository.findAllByTypeAndUserNameAndCycleNumber(Types.PICKS.name, userName, cycleNumber)
        }
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return PickUtil.parsePicks(picksList)
    }

    fun getByMatchId(matchId: Int): List<Pick> {
        val punditzResponse = punditzRepository.findAllByTypeAndMatchId(Types.PICKS.name, matchId)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return PickUtil.parsePicks(picksList)
    }

    // update
    fun update(pick: Pick) {
        val p = punditzRepository.findByTypeAndUserNameAndMatchId(Types.PICKS.name, pick.userName, pick.matchId)
        p.jsonData = PunditzUtil.toJson(pick).toString()
        punditzRepository.save(p)
    }

    // delete
    fun delete(pick: Pick) {
        val p = punditzRepository.findByTypeAndUserNameAndMatchId(Types.PICKS.name, pick.userName, pick.matchId)
        punditzRepository.delete(p)
    }
}