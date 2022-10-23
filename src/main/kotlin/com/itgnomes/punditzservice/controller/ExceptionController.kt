package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.model.Exception
import com.itgnomes.punditzservice.service.ExceptionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController(value="/cycles")
class ExceptionController(@Autowired private val exceptionService: ExceptionService) {

    @GetMapping("/{cycleNumber}")
    fun get(@PathVariable cycleNumber: Int): Exception {
        return exceptionService.getByCycleNumber(cycleNumber)
    }

    @GetMapping
    fun getAll(): List<Exception> {
        return exceptionService.getAll()
    }

    @PutMapping("/exceptions")
    fun updateExceptions(@RequestBody exception: Exception) {
        exceptionService.update(exception)
    }
}