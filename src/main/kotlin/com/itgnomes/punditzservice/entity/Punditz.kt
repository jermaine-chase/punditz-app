package com.itgnomes.punditzservice.entity

import org.springframework.data.annotation.Id
import javax.persistence.Table

@Table
data class Punditz(@Id
    var id: Int? = null,
    var type: String,
    var jsonData: String) {

}
