package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Pick
import com.itgnomes.punditzservice.service.PickService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class PickController(@Autowired private val pickService: PickService) {

    @GetMapping("/picks/{cycleNumber}")
    fun get(@PathVariable cycleNumber: Int, @RequestHeader userName: String): List<Pick> {
        return pickService.getByUserAndCycleNumber(userName, cycleNumber)
    }

    @GetMapping("/picks")
    fun getAll(): List<Pick> {
        return pickService.getAll()
    }

    @PostMapping("/picks")
    fun insert(@RequestBody pickList: List<Pick>): MutableList<String> {
        val updateResponses = mutableListOf<String>()
        pickList.forEach{
            pickService.insert(it)
            updateResponses.add("Inserted ${it.matchId} for ${it.userName}")
        }
        return updateResponses
    }

    @PutMapping("/picks")
    fun update(@RequestBody pickList: List<Pick>): MutableList<String> {
        val updateResponses = mutableListOf<String>()
        pickList.forEach{
            pickService.update(it)
            updateResponses.add("Updated ${it.matchId} for ${it.userName}")
        }
        return updateResponses
    }

    @DeleteMapping("/picks")
    fun delete(@RequestBody pickList: List<Pick>): MutableList<String> {
        val updateResponses = mutableListOf<String>()
        pickList.forEach{
            pickService.delete(it)
            updateResponses.add("Deleted ${it.matchId} for ${it.userName}")
        }
        return updateResponses
    }
}