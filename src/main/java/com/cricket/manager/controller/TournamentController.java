package com.cricket.manager.controller;

import com.cricket.manager.model.Tournament;
import com.cricket.manager.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"}) // React app's default ports
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.createTournament(tournament);
        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournamentDetails) {
        try {
            Tournament updatedTournament = tournamentService.updateTournament(id, tournamentDetails);
            return ResponseEntity.ok(updatedTournament);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        try {
            tournamentService.deleteTournament(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
