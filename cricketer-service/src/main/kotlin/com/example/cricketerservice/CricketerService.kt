package com.example.cricketerservice

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CricketerService(@Autowired val cricketerRepository: CricketerRepository) {
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
}