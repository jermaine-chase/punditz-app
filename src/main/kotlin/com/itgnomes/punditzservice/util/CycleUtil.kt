package com.itgnomes.punditzservice.util

import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Cycle
import com.itgnomes.punditzservice.model.Match

class CycleUtil {
    companion object {
        fun parseCycles(jsonList: List<String>): MutableList<Cycle> {
            val cycleList = mutableListOf<Cycle>()

            jsonList.forEach {
                parseCycle(it)
            }

            return cycleList
        }

        fun parseCycle(cycleStr: String): Cycle {
            return Gson().fromJson(cycleStr, Cycle::class.java)
        }

        fun calculateCycles(matches: List<Match>, leagueId: Int): MutableMap<Int, Cycle> {
            val cycleMap = mutableMapOf<Int, Cycle>()
            matches.forEach{
                val cycle: Cycle?
                val matchList = mutableListOf<Int>()

                if (!cycleMap.containsKey(it.matchday)) {
                    cycle = Cycle(it.matchday, matchList, null, null, null, leagueId, it.season?.id)
                    cycleMap[it.matchday] = cycle
                } else {
                    cycle = cycleMap[it.matchday]
                }

                cycle?.matchList?.add(it.id)
            }
            return cycleMap
        }
    }
}