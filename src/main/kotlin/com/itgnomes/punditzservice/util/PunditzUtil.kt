package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.entity.Punditz
import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Match
import com.itgnomes.punditzservice.model.Team

class PunditzUtil {
    companion object {
        @JvmStatic
        fun parseResponse(punditzList: List<Punditz>) : List<String> {
            val response = mutableListOf<String>()

            punditzList.forEach {
                response.add(it.jsonData)
            }
            return response
        }

        @JvmStatic
        fun toJson(obj: Any): String {
            return Gson().toJson(obj)
        }

        @JvmStatic
        fun parseMatches(str: String): MutableList<Match> {
            val list = mutableListOf<Match>()
            val jsonList = Gson().fromJson(str, mutableListOf<String>().javaClass)

            jsonList.forEach {
                list.add(parseMatch(it))
            }
            return list
        }

        @JvmStatic
        fun parseMatch(str: String): Match {
            return Gson().fromJson(str, Match::class.java)
        }

        @JvmStatic
        fun parseTeams(str: String): MutableList<Team> {
            val list = mutableListOf<Team>()
            val jsonList = Gson().fromJson(str, mutableListOf<String>().javaClass)

            jsonList.forEach {
                list.add(parseTeam(it))
            }
            return list
        }

        @JvmStatic
        fun parseTeam(str: String): Team {
            return Gson().fromJson(str, Team::class.java)
        }
    }
}