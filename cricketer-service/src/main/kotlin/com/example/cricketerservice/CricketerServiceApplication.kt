package com.example.cricketerservice

import lombok.extern.slf4j.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
@Slf4j
class CricketerServiceApplication

fun main(args: Array<String>) {
	runApplication<CricketerServiceApplication>(*args)
}
