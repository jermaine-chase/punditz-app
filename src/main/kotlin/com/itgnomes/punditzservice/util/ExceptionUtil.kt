package com.itgnomes.punditzservice.util

import com.google.gson.Gson
import com.itgnomes.punditzservice.model.Exception

class ExceptionUtil {
    companion object {
        fun parseExceptions(jsonList: List<String>): MutableList<Exception> {
            val cycleList = mutableListOf<Exception>()

            jsonList.forEach {
                parseException(it)
            }

            return cycleList
        }

        fun parseException(cycleStr: String): Exception {
            return Gson().fromJson(cycleStr, Exception::class.java)
        }
    }
}