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
                cycle.startDate = PunditzUtil.earlier(it.utcDate, cycle.startDate!!)
                cycle.endDate = PunditzUtil.later(it.utcDate, cycle.endDate!!)
            }
            return cycleMap
        }
    }
}