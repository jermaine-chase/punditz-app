package com.itgnomes.punditzservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("pundit")
data class Punditz(@Id
    var id: Int? = null,
    var type: String,
    var jsonData: String) {

}
