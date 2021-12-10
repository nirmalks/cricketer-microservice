package com.example.cricketerservice

import com.example.feignclients.TeamFeignClient
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CricketerService(@Autowired val cricketerRepository: CricketerRepository) {
    @Autowired
    lateinit var teamFeignClient: TeamFeignClient

    suspend fun save(cricketer: Cricketer): Cricketer {
        return cricketerRepository.save(cricketer).awaitFirst()
    }

    suspend fun findById(id:String): Cricketer? {
        return cricketerRepository.findById(id).awaitFirstOrNull()
    }

    @FlowPreview
    fun getAllPlayers(): Flow<Cricketer> {
        return cricketerRepository.findAll().asFlow()
    }

    suspend fun deleteById(id: String): Void? {
        return cricketerRepository.deleteById(id).awaitFirstOrNull()
    }

    fun getTeamById(id: String): Team? {
        return teamFeignClient.getTeam(id)
    }
}