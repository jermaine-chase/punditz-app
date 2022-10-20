package com.itgnomes.punditzservice.util

import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Cycle

class CycleUtil {
    companion object {
        @JvmStatic
        fun parseCycles(jsonList: List<String>): MutableList<Cycle> {
            val cycleList = mutableListOf<Cycle>()

            jsonList.forEach {
                parseCycle(it)
            }

            return cycleList
        }

        @JvmStatic
        fun parseCycle(cycleStr: String): Cycle {
            return Gson().fromJson(cycleStr, Cycle::class.java)
        }
    }
}