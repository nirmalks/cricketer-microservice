package com.example.feignclients

import feign.Feign
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient
import org.springframework.context.annotation.Bean

@LoadBalancerClient(value = "team-service")
class TeamLoadBalancerConfig {

    @LoadBalanced
    @Bean
    fun feignBuilder(): Feign.Builder = Feign.builder()
}