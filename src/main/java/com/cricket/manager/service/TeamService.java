package com.cricket.manager.service;

import com.cricket.manager.model.Team;
import com.cricket.manager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team teamDetails) {
        return teamRepository.findById(id).map(team -> {
            team.setName(teamDetails.getName());
            team.setShortName(teamDetails.getShortName());
            team.setCoach(teamDetails.getCoach());
            team.setHomeVenue(teamDetails.getHomeVenue());
            return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
