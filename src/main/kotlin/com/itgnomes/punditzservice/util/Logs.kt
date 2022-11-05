package com.itgnomes.punditzservice.util

import org.apache.logging.log4j.kotlin.Logging
import kotlin.reflect.KClass

class Logs {
    companion object: Logging {
        fun <T: Any> info(clazz: KClass<T>, message: String) {
            logger.info("${clazz.simpleName} - $message")
        }

        fun <T: Any> debug(clazz: KClass<T>, message: String) {
            logger.debug("${clazz.simpleName} - $message")
        }

        fun <T: Any> error(clazz: KClass<T>, message: String) {
            logger.error("${clazz.simpleName} - $message")
        }
    }
}