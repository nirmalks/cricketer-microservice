package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean

@EnableEurekaClient
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Bean
fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
    return builder.routes()
            .route (
                "cricketer-service"
            ) { r -> r.path("/api/cricketers")
                    .uri("lb://cricketer-service")
            }
            .build()
}
