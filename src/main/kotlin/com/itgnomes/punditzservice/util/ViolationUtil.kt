package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.model.Violation

class ViolationUtil {
    companion object {
        @JvmStatic
        fun parseViolations(jsonList: List<String>): MutableList<Violation> {
            val list = mutableListOf<Violation>()

            jsonList.forEach {
                parseViolation(it)
            }

            return list
        }

        @JvmStatic
        fun parseViolation(str: String): Violation {
            return PunditzUtil.parse(str, Violation::class)
        }
    }
}