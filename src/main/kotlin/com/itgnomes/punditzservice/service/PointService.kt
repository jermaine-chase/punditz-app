package com.itgnomes.punditzservice.service

import com.itgnomes.punditzservice.entity.Punditz
import com.itgnomes.punditzservice.model.Point
import com.itgnomes.punditzservice.repository.PunditzRepository
import com.itgnomes.punditzservice.util.PointUtil
import com.itgnomes.punditzservice.util.PunditzUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.itgnomes.punditzservice.util.Types

@Service
class PointService(@Autowired private val punditzRepository : PunditzRepository) {

    // create
    fun insert(p: Point) {
        val jsonData = PunditzUtil.toJson(p)
        punditzRepository.save(Punditz(null, Types.POINTS.type, jsonData))
    }

    // gets
    fun getAll() : List<Point> {
        val punditzResponse = punditzRepository.findAllByType(Types.POINTS.name)
        return PointUtil.parsePoints(PunditzUtil.parseResponse(punditzResponse))
    }

    fun getByUser(userName: String): List<Point> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.POINTS.name, userName)
        val pointsList = PunditzUtil.parseResponse(punditzResponse)
        return PointUtil.parsePoints(pointsList)
    }

    fun getByUserAndCycleNumber(userName: String, cycleNumber: Int): List<Point> {
        val punditzResponse = punditzRepository.findAllByTypeAndUserName(Types.POINTS.name, userName)
        val pointsList = PunditzUtil.parseResponse(punditzResponse)
        return PointUtil.parsePoints(pointsList)
    }

    // update
    fun update(point: Point) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.POINTS.name, point.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        p.jsonData = PunditzUtil.toJson(point)
        punditzRepository.save(p)
    }

    // delete
    fun delete(point: Point) {
        val pList = punditzRepository.findAllByTypeAndUserName(Types.POINTS.name, point.userName)
        // There can only be one. - Colin MacLeod of the Clan MacLeod
        val p = pList[0]
        punditzRepository.delete(p)
    }
}