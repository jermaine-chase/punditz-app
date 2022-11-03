package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.model.PunditScore

class ScoreUtil {
    companion object {
        @JvmStatic
        fun parseScores(jsonList: List<String>): MutableList<PunditScore> {
            val list = mutableListOf<PunditScore>()

            jsonList.forEach {
                parseScore(it)
            }

            return list
        }

        @JvmStatic
        fun parseScore(str: String): PunditScore {
            return PunditzUtil.parse(str, PunditScore::class)
        }
    }
}