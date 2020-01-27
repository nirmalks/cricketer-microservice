package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI

@EnableEurekaClient
@SpringBootApplication
class DemoApplication {

    fun main(args: Array<String>) {
        runApplication<DemoApplication>(*args)
    }
    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
                .route (
                        "cricketer-service"
                ) { r -> r.path("/api/cricketers/**")
                        .filters { filter ->
                            filter.hystrix { config ->
                                run {
                                    config.fallbackUri =
                                            URI("forward:/cricketers-fallback")
                                }
                            }
                        }
                        .uri("http://localhost:8081/")
                }
                .build()
    }


    @Bean
    @LoadBalanced
    fun loadBalancedWebClientBuilder():WebClient.Builder {
        return WebClient.builder()
    }
}



