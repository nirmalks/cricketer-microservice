package com.example.cricketerservice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function


@RestController
class CricketerController(private val cricketerService: CricketerService,
                          private val cricketerRepository: CricketerRepository) {
    @GetMapping("/cricketers")
    fun getAllCricketers(): Flux<Cricketer?>? {
        return cricketerRepository.findAll()
    }

    @GetMapping("/cricketers/{id}")
    fun getCricketer(@PathVariable("id") id: String): Mono<ResponseEntity<Cricketer>> {
        return cricketerRepository.findById(id)
                .map(Function<Cricketer, ResponseEntity<Cricketer>> { body: Cricketer? -> ResponseEntity.ok(body!!) })
                .defaultIfEmpty(ResponseEntity<Cricketer>(HttpStatus.NOT_FOUND))
    }

    @PostMapping("/cricketers")
    fun addCricketer(@RequestBody cricketer: Cricketer):
            Mono<ResponseEntity<Cricketer>>? {
        return cricketerRepository.save(cricketer).map {
            ResponseEntity(it, HttpStatus.CREATED)
        }
    }

    @PutMapping("/cricketers/{id}")
    fun updateCricketer(@PathVariable("id") id: String, @RequestBody cricketer: Cricketer): Mono<ResponseEntity<Cricketer>> {
        return cricketerRepository.findById(id).flatMap(Function<Cricketer, Mono<out Cricketer?>> { currentCricketer: Cricketer ->
            currentCricketer.copy(country = cricketer.country, name = cricketer.name,
                    highestScore = cricketer.highestScore)
            cricketerRepository.save<Cricketer?>(currentCricketer)
        })
                .map( { updatedCricketer: Cricketer? -> ResponseEntity(updatedCricketer, HttpStatus.OK) })
                .defaultIfEmpty(ResponseEntity<Cricketer>(HttpStatus.NOT_FOUND))
    }

    @DeleteMapping("/cricketers/{id}")
    fun deleteCricketer(@PathVariable("id") id: String): Mono<ResponseEntity<Void>> {
        return cricketerRepository.deleteById(id).then(Mono.just(ResponseEntity<Void>(HttpStatus.OK)
        ))
    }
}