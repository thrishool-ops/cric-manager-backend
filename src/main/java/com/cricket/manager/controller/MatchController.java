package com.cricket.manager.controller;

import com.cricket.manager.model.Match;
import com.cricket.manager.service.MatchService;
import com.cricket.manager.dto.LiveScoreUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/recent")
    public List<Match> getRecentMatches() {
        return matchService.getRecentMatches();
    }

    @GetMapping("/team/{teamId}")
    public List<Match> getMatchesByTeam(@PathVariable Long teamId) {
        return matchService.getMatchesByTeam(teamId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return new ResponseEntity<>(matchService.createMatch(match), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        try {
            return ResponseEntity.ok(matchService.updateMatch(id, match));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<Match> updateLiveScore(@PathVariable Long id, @RequestBody LiveScoreUpdateRequest request) {
        try {
            return ResponseEntity.ok(matchService.updateLiveScore(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
