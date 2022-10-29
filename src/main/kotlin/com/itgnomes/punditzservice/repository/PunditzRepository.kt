package com.itgnomes.punditzservice.repository

import com.itgnomes.punditzservice.entity.Punditz
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PunditzRepository: CrudRepository<Punditz, Long> {
    fun findAllByType(type: String): List<Punditz>

    @Query("SELECT p FROM Punditz p WHERE p.type = ?1 AND JSON_VALUE(p.jsonData, '\$.userName') = ?2")
    fun findAllByTypeAndUserName(type: String, userName: String): List<Punditz>

    @Query("SELECT p FROM Punditz p WHERE p.type = ?1 AND JSON_VALUE(p.jsonData, '\$.cycleNumber') = ?2")
    fun findByTypeAndCycleNumber(type: String, cycleNumber: Int): Punditz

    @Query("SELECT p FROM Punditz p WHERE p.type = ?1 AND JSON_VALUE(p.jsonData, '\$.userName') = ?2 AND JSON_VALUE(p.jsonData, '\$.matchId') = ?3")
    fun findByTypeAndUserNameAndMatchId(type: String, userName: String, matchId: Int): Punditz

    @Query("SELECT p FROM Punditz p WHERE p.type = ?1 AND JSON_VALUE(p.jsonData, '\$.userName') = ?2 AND JSON_VALUE(p.jsonData, '\$.cycleNumber') = ?3")
    fun findAllByTypeAndUserNameAndCycleNumber(type: String, userName: String, cycleNumber: Int): List<Punditz>

    @Query("SELECT p FROM Punditz p WHERE p.type = ?1 AND JSON_VALUE(p.jsonData, '\$.matchId') = ?2")
    fun findAllByTypeAndMatchId(type: String, matchId: Int): List<Punditz>
}