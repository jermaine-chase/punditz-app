package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.PunditzException
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
    fun get(@PathVariable exceptionCycle: Int): PunditzException {
        return exceptionService.getByCycleNumber(exceptionCycle)
    }

    @GetMapping("/excepts")
    fun getAll(): List<PunditzException> {
        return exceptionService.getAll()
    }

    @PostMapping("/excepts")
    fun create(@RequestBody exception: PunditzException) {
        exceptionService.insert(exception)
    }

    @PutMapping("/excepts")
    fun update(@RequestBody exception: PunditzException) {
        exceptionService.update(exception)
    }
}