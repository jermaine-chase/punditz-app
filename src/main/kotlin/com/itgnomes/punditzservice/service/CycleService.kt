package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.CycleUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class CycleService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(c: Cycle) {
        val jsonData = PunditzUtil.toJson(c).toString()
       punditzRepository.save(Punditz(null, Types.CYCLES.type, jsonData))
    }

    // gets
    fun getAll() : List<Cycle> {
        val punditzResponse = punditzRepository.findAllByType(Types.CYCLES.name)
        return CycleUtil.parseCycles(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByCycleNumber(cycleNumber: Int): Cycle {
        val punditzResponse = punditzRepository.findByTypeAndCycleNumber(Types.CYCLES.name, cycleNumber)
        return CycleUtil.parseCycle(punditzResponse.jsonData)
    }

    // update
    fun update(c: Cycle) {
        val p = punditzRepository.findByTypeAndCycleNumber(Types.CYCLES.name, c.cycleNumber)
        p.jsonData = PunditzUtil.toJson(c).toString()
        punditzRepository.save(p)
    }

    // delete
    fun delete(c: Cycle) {
        val p = punditzRepository.findByTypeAndCycleNumber(Types.CYCLES.name, c.cycleNumber)
        punditzRepository.delete(p)
    }
}