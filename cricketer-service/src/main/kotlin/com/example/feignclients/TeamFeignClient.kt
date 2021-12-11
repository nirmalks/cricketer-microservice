package com.example.feignclients

import com.example.cricketerservice.Team
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "team-service", path = "/api/teams")
interface TeamFeignClient {
    @GetMapping("/{id}", consumes = ["application/json"])
    fun getTeam(@PathVariable("id") id: String): Team
}