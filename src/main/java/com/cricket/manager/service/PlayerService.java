package com.cricket.manager.service;

import com.cricket.manager.model.Player;
import com.cricket.manager.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public List<Player> searchPlayersByName(String name) {
        return playerRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Player> filterPlayersByRole(String role) {
        return playerRepository.findByRole(role);
    }

    public List<Player> getTopBatsmen() {
        return playerRepository.findTop10ByOrderByRunsDesc();
    }

    public List<Player> getTopBowlers() {
        return playerRepository.findTop10ByOrderByWicketsDesc();
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player playerDetails) {
        return playerRepository.findById(id).map(player -> {
            player.setName(playerDetails.getName());
            player.setAge(playerDetails.getAge());
            player.setRole(playerDetails.getRole());
            player.setBattingStyle(playerDetails.getBattingStyle());
            player.setBowlingStyle(playerDetails.getBowlingStyle());
            player.setCountry(playerDetails.getCountry());
            player.setTeam(playerDetails.getTeam());
            
            // Stats
            player.setMatchesPlayed(playerDetails.getMatchesPlayed());
            player.setRuns(playerDetails.getRuns());
            player.setWickets(playerDetails.getWickets());
            player.setStrikeRate(playerDetails.getStrikeRate());
            player.setBattingAverage(playerDetails.getBattingAverage());
            player.setEconomyRate(playerDetails.getEconomyRate());
            player.setBestPerformance(playerDetails.getBestPerformance());
            player.setContactDetails(playerDetails.getContactDetails());
            player.setProfileImageUrl(playerDetails.getProfileImageUrl());
            
            return playerRepository.save(player);
        }).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
