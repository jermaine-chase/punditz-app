package com.itgnomes.punditzservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.itgnomes.punditzservice.entity")
class PunditzServiceApplication

fun main(args: Array<String>) {
    runApplication<PunditzServiceApplication>(*args)
}
