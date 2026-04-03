package com.cricket.manager.controller;

import com.cricket.manager.model.Player;
import com.cricket.manager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Long teamId) {
            
        if (search != null && !search.isEmpty()) {
            return playerService.searchPlayersByName(search);
        }
        if (role != null && !role.isEmpty()) {
            return playerService.filterPlayersByRole(role);
        }
        if (teamId != null) {
            return playerService.getPlayersByTeam(teamId);
        }
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/leaderboard/batsmen")
    public List<Player> getTopBatsmen() {
        return playerService.getTopBatsmen();
    }

    @GetMapping("/leaderboard/bowlers")
    public List<Player> getTopBowlers() {
        return playerService.getTopBowlers();
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        try {
            return ResponseEntity.ok(playerService.updatePlayer(id, playerDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
