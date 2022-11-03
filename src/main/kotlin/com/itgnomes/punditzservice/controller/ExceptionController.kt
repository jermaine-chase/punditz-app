package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Exception
import com.itgnomes.punditzservice.service.ExceptionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class ExceptionController(@Autowired private val exceptionService: ExceptionService) {

    @GetMapping("/excepts/{exceptionCycle}")
    fun get(@PathVariable exceptionCycle: Int): Exception {
        return exceptionService.getByCycleNumber(exceptionCycle)
    }

    @GetMapping("/excepts")
    fun getAll(): List<Exception> {
        return exceptionService.getAll()
    }

    @PostMapping("/excepts")
    fun create(@RequestBody exception: Exception) {
        exceptionService.insert(exception)
    }

    @PutMapping("/excepts")
    fun update(@RequestBody exception: Exception) {
        exceptionService.update(exception)
    }
}