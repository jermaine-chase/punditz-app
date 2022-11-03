package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.model.Pick

class PickUtil {
    companion object {
        @JvmStatic
        fun parsePicks(jsonList: List<String>): MutableList<Pick> {
            val list = mutableListOf<Pick>()

            jsonList.forEach {
                parsePick(it)
            }

            return list
        }

        @JvmStatic
        fun parsePick(str: String): Pick {
            return PunditzUtil.parse(str, Pick::class)
        }
    }
}