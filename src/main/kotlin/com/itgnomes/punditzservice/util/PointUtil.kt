package com.itgnomes.punditzservice.util

import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Point

class PointUtil {
    companion object {
        @JvmStatic
        fun parsePoints(jsonList: List<String>): MutableList<Point> {
            val list = mutableListOf<Point>()

            jsonList.forEach {
                parsePoint(it)
            }

            return list
        }

        @JvmStatic
        fun parsePoint(str: String): Point {
            return Gson().fromJson(str, Point::class.java)
        }
    }
}