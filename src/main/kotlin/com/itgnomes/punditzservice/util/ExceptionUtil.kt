package com.itgnomes.punditzservice.util

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.PunditzException

class ExceptionUtil {
    companion object {
        fun parseExceptions(jsonList: List<Punditz>): MutableList<PunditzException> {
            val cycleList = mutableListOf<PunditzException>()

            jsonList.forEach {
                parseException(it.jsonData)
            }

            return cycleList
        }

        fun parseException(cycleStr: String): PunditzException {
            return PunditzUtil.parse(cycleStr, PunditzException::class)
        }
    }
}