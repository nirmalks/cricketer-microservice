package com.example.team

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TeamController(private val teamService: TeamService) {
    @GetMapping("/teams")
    @FlowPreview
    fun getAllTeams(): Flow<Team?>? {
        return teamService.getAllPlayers()
    }

    @GetMapping("/teams/{id}")
    suspend fun getTeam(@PathVariable("id") id: String): ResponseEntity<Team> {
        val team: Team = teamService.findById(id) ?:
        return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(team)
    }

    @PostMapping("/teams")
    suspend fun addTeam(@RequestBody cricketer: Team): ResponseEntity<Team> {
        return ResponseEntity(teamService.save(cricketer), HttpStatus.CREATED)
    }

    @PutMapping("/teams/{id}")
    suspend fun updateTeam(@PathVariable("id") id: String, @RequestBody team: Team): ResponseEntity<Team> {
        val existingTeam = teamService.findById(id) ?:
        return ResponseEntity(HttpStatus.NOT_FOUND)
        val newCricketer = teamService.save(existingTeam.copy( name = team.name,
                baseCountry = team.baseCountry, type = team.type))
        return ResponseEntity(newCricketer, HttpStatus.OK)
    }

    @DeleteMapping("/cricketers/{id}")
    suspend fun deleteTeam(@PathVariable("id") id: String): ResponseEntity<Void> {
        if(teamService.findById(id) == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(teamService.deleteById(id), HttpStatus.OK)
    }
}