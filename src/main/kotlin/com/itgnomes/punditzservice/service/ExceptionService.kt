package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.PunditzException
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.ExceptionUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class ExceptionService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(e: PunditzException) {
        val jsonData = PunditzUtil.toJson(e).toString()
       punditzRepository.save(Punditz(null, Types.EXCEPTION.type, jsonData))
    }

    // gets
    fun getAll() : List<PunditzException> {
        val punditzResponse = punditzRepository.findAllByType(Types.EXCEPTION.name)
        return ExceptionUtil.parseExceptions(punditzResponse)
    }

    fun getByCycleNumber(cycleNumber: Int): PunditzException {
        val punditzResponse = punditzRepository.findAllByTypeAndCycleNumber(Types.EXCEPTION.name, cycleNumber)
        val exceptions = ExceptionUtil.parseExceptions(punditzResponse)
        if (exceptions.size > 1) {
            throw Exception("Update Exception: More than one Exception object found for a cycle! Contact Admin!")
        }
        if (exceptions.isNotEmpty()) {
            return exceptions[0]
        }
        return PunditzException(0, mapOf(), "", 0)
    }

    // update
    fun update(e: PunditzException) {
        val p = punditzRepository.findAllByTypeAndCycleNumber(Types.EXCEPTION.name, e.cycleNumber)
        if (p.size > 1) {
            throw Exception("Update Exception: More than one Exception object found for a cycle! Contact Admin!")
        }
        if (p.isNotEmpty()) {
            p[0].jsonData = PunditzUtil.toJson(e).toString()
            punditzRepository.save(p[0])
        }
    }

    // delete
    fun delete(e: PunditzException) {
        val p = punditzRepository.findAllByTypeAndCycleNumber(Types.EXCEPTION.name, e.cycleNumber)
        if (p.size > 1) {
            throw Exception("Delete Exception: More than one Exception object found for a cycle! Contact Admin!")
        }
        if (p.isNotEmpty()) {
            punditzRepository.delete(p[0])
        }
    }
}