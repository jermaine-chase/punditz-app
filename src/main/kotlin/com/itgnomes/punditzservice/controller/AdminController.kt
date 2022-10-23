package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Point
import com.itgnomes.punditzservice.service.PointService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController(value="/admin")
class AdminController/*(@Autowired private val pointService: PointService)*/ {

    /*@GetMapping("/p")
    fun get(@RequestHeader userName: String): List<Point> {
        return pointService.getByUser(userName)
    }

    @GetMapping
    fun getAll(): List<Point> {
        return pointService.getAll()
    }

    @PostMapping
    fun insert(@RequestBody point: Point): String {
        pointService.insert(point)
        return "Inserted points for ${point.userName}"
    }

    @PutMapping
    fun update(@RequestBody point: Point): String {
        pointService.update(point)
        return "Updated points for ${point.userName}"
    }

    @DeleteMapping("/points")
    fun delete(@RequestBody point: Point): String {
        pointService.delete(point)
        return "Deleted points for ${point.userName}"
    }*/
}