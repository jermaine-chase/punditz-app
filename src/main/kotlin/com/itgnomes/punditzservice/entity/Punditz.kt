package com.itgnomes.punditzservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("pundit")
data class Punditz(@Id val id: Int?, val type: String, var jsonData: String)
