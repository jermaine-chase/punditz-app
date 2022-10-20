package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.Prediction
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.PredictionUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class PredictionService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(p: Prediction) {
        val jsonData = PunditzUtil.toJson(p)
        punditzRepository.save(Punditz(null, Types.PREDICTIONS.type, jsonData))
    }

    // gets
    fun getAll() : List<Prediction> {
        val punditzResponse = punditzRepository.findAllByType(Types.PREDICTIONS.name)
        return PredictionUtil.parsePredictions(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByUser(userName: String): List<Prediction> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.PREDICTIONS.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return PredictionUtil.parsePredictions(picksList)
    }

    fun getByUserAndCycleNumber(userName: String, cycleNumber: Int): List<Prediction> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.PREDICTIONS.name, userName)
        val picksList = PunditzUtil.parseResponse(punditzResponse)
        return PredictionUtil.parsePredictions(picksList)
    }

    // update
    fun update(prediction: Prediction) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.PREDICTIONS.name, prediction.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        p.jsonData = PunditzUtil.toJson(prediction)
        punditzRepository.save(p)
    }

    // delete
    fun delete(prediction: Prediction) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.PREDICTIONS.name, prediction.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        punditzRepository.delete(p)
    }
}