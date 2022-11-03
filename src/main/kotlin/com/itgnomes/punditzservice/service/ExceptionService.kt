package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.Exception
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.ExceptionUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class ExceptionService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(e: Exception) {
        val jsonData = PunditzUtil.toJson(e).toString()
       punditzRepository.save(Punditz(null, Types.EXCEPTION.type, jsonData))
    }

    // gets
    fun getAll() : List<Exception> {
        val punditzResponse = punditzRepository.findAllByType(Types.EXCEPTION.name)
        return ExceptionUtil.parseExceptions(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByCycleNumber(cycleNumber: Int): Exception {
        val punditzResponse = punditzRepository.findByTypeAndCycleNumber(Types.EXCEPTION.name, cycleNumber)
        return ExceptionUtil.parseException(punditzResponse.jsonData)
    }

    // update
    fun update(e: Exception) {
        val p = punditzRepository.findByTypeAndCycleNumber(Types.EXCEPTION.name, e.cycleNumber)
        p.jsonData = PunditzUtil.toJson(e).toString()
        punditzRepository.save(p)
    }

    // delete
    fun delete(e: Exception) {
        val p = punditzRepository.findByTypeAndCycleNumber(Types.EXCEPTION.name, e.cycleNumber)
        punditzRepository.delete(p)
    }
}