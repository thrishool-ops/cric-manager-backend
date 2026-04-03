package com.cricket.manager.service;

import com.cricket.manager.model.Tournament;
import com.cricket.manager.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament tournamentDetails) {
        return tournamentRepository.findById(id).map(tournament -> {
            tournament.setName(tournamentDetails.getName());
            tournament.setLocation(tournamentDetails.getLocation());
            tournament.setStartDate(tournamentDetails.getStartDate());
            tournament.setEndDate(tournamentDetails.getEndDate());
            tournament.setStatus(tournamentDetails.getStatus());
            return tournamentRepository.save(tournament);
        }).orElseThrow(() -> new RuntimeException("Tournament not found with id " + id));
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
