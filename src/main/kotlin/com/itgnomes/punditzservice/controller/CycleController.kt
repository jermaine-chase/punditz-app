package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.service.FootBallApiService
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired

@RestController(value="/cycles")
class CycleController(@Autowired private val footBallApiService: FootBallApiService) {

    @GetMapping("/{cycleNumber}")
    fun get(@PathVariable cycleNumber: Int): Cycle? {
        return footBallApiService.getCycle(cycleNumber)
    }

    @GetMapping
    fun getAll(): List<Cycle> {
        return footBallApiService.getCycleList(PunditzUtil.EPL)
    }
}