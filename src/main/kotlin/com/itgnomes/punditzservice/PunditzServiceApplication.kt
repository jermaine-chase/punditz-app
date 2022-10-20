package com.itgnomes.punditzservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PunditzServiceApplication

fun main(args: Array<String>) {
    runApplication<PunditzServiceApplication>(*args)
}
