package com.itgnomes.punditzservice.util

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
            return PunditzUtil.parse(cycleStr, Cycle::class)
        }

        fun calculateCycles(matches: List<Match>, leagueId: Int): MutableMap<Int, Cycle> {
            val cycleMap = mutableMapOf<Int, Cycle>()
            matches.forEach{
                val cycle: Cycle
                val matchList = mutableListOf<Int>()

                if (!cycleMap.containsKey(it.matchday)) {
                    cycle = Cycle(it.matchday, matchList, null, it.utcDate, it.utcDate, leagueId, it.season?.id)
                    cycleMap[it.matchday] = cycle
                } else {
                    cycle = cycleMap[it.matchday]!!
                }

                cycle.matchList.add(it.id)
                cycle.startDate = earlier(it.utcDate, cycle.startDate!!)
                cycle.endDate = later(it.utcDate, cycle.endDate!!)
            }
            return cycleMap
        }

        private fun earlier(d1: String, d2: String) = if (d1.compareTo(d2) <= 0) d1 else  d2

        private fun later(d1: String, d2: String) = if (d1.compareTo(d2) >= 0) d1 else  d2
    }
}