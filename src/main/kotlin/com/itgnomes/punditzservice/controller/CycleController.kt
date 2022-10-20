package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.model.Exception
import com.itgnomes.punditzservice.service.CycleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController(value="/cycles")
class CycleController(@Autowired private val cycleService: CycleService) {

    @GetMapping("/{cycleNumber}")
    fun get(@PathVariable cycleNumber: Int): Cycle {
        return cycleService.getByCycleNumber(cycleNumber)
    }

    @GetMapping
    fun getAll(): List<Cycle> {
        return cycleService.getAll()
    }

    @PutMapping("/exceptions")
    fun updateExceptions(@RequestBody exception: Exception) {
        val c = cycleService.getByCycleNumber(exception.cycleNumber)
        c.exception = exception
        cycleService.update(c)
    }
}