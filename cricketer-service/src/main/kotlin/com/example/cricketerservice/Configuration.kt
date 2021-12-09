package com.example.cricketerservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class MyConfig {
    @Value("\${team.service.url}")
    lateinit var teamServiceUrl: String

    @Bean
    fun webClient() = WebClient.builder().baseUrl(teamServiceUrl).build()
}