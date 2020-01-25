package com.example.cricketerservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CricketerService(@Autowired val cricketerRepository: CricketerRepository) {
    fun save(cricketer: Cricketer ): Unit {
        cricketerRepository.save(cricketer)
    }

    fun findById(id:String): Mono<Cricketer> {
        return cricketerRepository.findById(id)
    }

    fun getAllPlayers(): Flux<Cricketer> {
        return cricketerRepository.findAll()
    }
}