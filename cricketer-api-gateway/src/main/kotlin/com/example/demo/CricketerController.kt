package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@RestController
class CricketerController(@Autowired val cricketerClient: WebClient.Builder) {

    @GetMapping("/cricketers-fallback")
    fun cricketersFallback(): Flux<Cricketer> {
        return Flux.empty()
    }

}