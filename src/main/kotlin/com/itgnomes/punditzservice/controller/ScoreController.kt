package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Score
import com.itgnomes.punditzservice.service.ScoreService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController(value="/scores")
class ScoreController(@Autowired private val scoreService: ScoreService) {

    @GetMapping("/p")
    fun get(@RequestHeader userName: String): List<Score> {
        return scoreService.getByUser(userName)
    }

    @GetMapping
    fun getAll(): List<Score> {
        return scoreService.getAll()
    }

    @PostMapping
    fun insert(@RequestBody score: Score): String {
        scoreService.insert(score)
        return "Inserted scores for ${score.userName}"
    }

    @PutMapping
    fun update(@RequestBody score: Score): String {
        scoreService.update(score)
        return "Updated scores for ${score.userName}"
    }

    @DeleteMapping
    fun delete(@RequestBody score: Score): String {
        scoreService.delete(score)
        return "Deleted scores for ${score.userName}"
    }
}