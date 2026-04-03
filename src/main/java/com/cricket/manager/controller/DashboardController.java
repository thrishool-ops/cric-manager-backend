package com.cricket.manager.controller;

import com.cricket.manager.repository.MatchRepository;
import com.cricket.manager.repository.PlayerRepository;
import com.cricket.manager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.cricket.manager.repository.TournamentRepository;
import com.cricket.manager.config.DataSeeder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class DashboardController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private DataSeeder dataSeeder;

    @PostMapping("/seed")
    public ResponseEntity<Map<String, String>> triggerSeeding() {
        dataSeeder.seedData(teamRepository, playerRepository, matchRepository, tournamentRepository);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Database seeding triggered successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPlayers", playerRepository.count());
        stats.put("totalTeams", teamRepository.count());
        stats.put("totalMatches", matchRepository.count());
        
        // Find top performer (simplified logic for dashboard - just top run scorer)
        playerRepository.findTop10ByOrderByRunsDesc().stream().findFirst().ifPresent(player -> 
            stats.put("topPerformer", player.getName() + " (" + player.getRuns() + " runs)")
        );

        return stats;
    }
}
