package com.itgnomes.punditzservice.entity

import javax.persistence.*

@Entity
data class Punditz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var type: String,
    var jsonData: String)
