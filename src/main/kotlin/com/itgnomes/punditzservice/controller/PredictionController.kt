package com.itgnomes.punditzservice.controller

import com.itgnomes.punditzservice.model.Prediction
import com.itgnomes.punditzservice.service.PredictionService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController(value="/predictions")
class PredictionController(@Autowired private val predictionService: PredictionService) {

    @GetMapping("/p")
    fun get(@RequestHeader userName: String): List<Prediction> {
        return predictionService.getByUser(userName)
    }

    @GetMapping
    fun getAll(): List<Prediction> {
        return predictionService.getAll()
    }

    @PostMapping
    fun insert(@RequestBody prediction: Prediction): String {
        predictionService.insert(prediction)
        return "Inserted predictions for ${prediction.userName}"
    }

    @PutMapping
    fun update(@RequestBody prediction: Prediction): String {
        predictionService.update(prediction)
        return "Updated predictions for ${prediction.userName}"
    }

    @DeleteMapping
    fun delete(@RequestBody prediction: Prediction): String {
        predictionService.delete(prediction)
        return "Deleted predictions for ${prediction.userName}"
    }
}