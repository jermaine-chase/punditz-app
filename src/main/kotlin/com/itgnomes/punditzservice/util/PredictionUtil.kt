package com.itgnomes.punditzservice.util

import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Prediction

class PredictionUtil {
    companion object {
        @JvmStatic
        fun parsePredictions(jsonList: List<String>): MutableList<Prediction> {
            val list = mutableListOf<Prediction>()

            jsonList.forEach {
                parsePrediction(it)
            }

            return list
        }

        @JvmStatic
        fun parsePrediction(str: String): Prediction {
            return Gson().fromJson(str, Prediction::class.java)
        }
    }
}