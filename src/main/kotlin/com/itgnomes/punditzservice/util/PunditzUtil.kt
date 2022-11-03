package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.entity.Punditz
import com.google.gson.GsonBuilder
import com.itgnomes.punditzservice.model.Match
import com.itgnomes.punditzservice.model.Team
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

class PunditzUtil {
    companion object {

        const val EPL = 2021
        const val EURO = 2018
        private const val DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val GSON = GsonBuilder().setDateFormat(DATEFORMAT).create()

        @JvmStatic
        fun parseResponse(punditzList: List<Punditz>) : List<String> {
            val response = mutableListOf<String>()

            punditzList.forEach {
                response.add(it.jsonData)
            }
            return response
        }

        @JvmStatic
        fun <T : Any> parse(input: String, clazz: KClass<T>): T {
            return GSON.fromJson(input, clazz.java)
        }

        @JvmStatic
        fun <T : Any> parseMany(input: String, clazz: KClass<T>): MutableList<T> {
            val p = JSONArray(input)
            val list = mutableListOf<T>()
            for (i in 0 until p.length()) {
                list.add(parse(p.getJSONObject(i).toString(), clazz))
            }
            return list
        }

        @JvmStatic
        fun toJson(obj: Any): JSONObject {
            val o = GSON.toJson(obj)
            return JSONObject(o)
        }

        fun parseMatches(str: String): MutableList<Match> {
            val strJson = JSONObject(str)
            return parseMany(strJson.getJSONArray("matches").toString(), Match::class)
        }

        fun parseTeams(str: String): MutableList<Team> {
            val list = mutableListOf<Team>()
            val jsonList = GSON.fromJson(str, mutableListOf<String>().javaClass)

            jsonList.forEach {
                list.add(parse(it, Team::class))
            }
            return list
        }

        @JvmStatic
        fun parseTeam(t: JSONObject): Team {
            return GSON.fromJson(t.toString(), Team::class.java)
        }
    }
}