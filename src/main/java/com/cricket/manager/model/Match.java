package com.cricket.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    private Team teamB;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private String venue;
    private LocalDate matchDate;
    private String matchType; // ODI, T20, Test
    
    // Scorecard details
    private Integer teamARuns = 0;
    private Integer teamAWickets = 0;
    private Double teamAOvers = 0.0;
    
    private Integer teamBRuns = 0;
    private Integer teamBWickets = 0;
    private Double teamBOvers = 0.0;
    
    private String winner; 
    
    @Column(length = 1000)
    private String matchSummary;

    // Live Scoring Details
    private String status = "UPCOMING"; // UPCOMING, LIVE, COMPLETED
    private Integer currentInnings = 1;
    private String currentStriker = "";
    private String currentNonStriker = "";
    private String currentBowler = "";
    private String currentOverDetails = "";
    private Integer targetScore = 0;
    private String tossResult = "";
    
    @ManyToMany
    @JoinTable(
        name = "match_playing_xi",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> playingXI = new ArrayList<>();

    public Match() {}

    public Match(Team teamA, Team teamB, String venue, LocalDate matchDate, String matchType) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.venue = venue;
        this.matchDate = matchDate;
        this.matchType = matchType;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Team getTeamA() { return teamA; }
    public void setTeamA(Team teamA) { this.teamA = teamA; }

    public Team getTeamB() { return teamB; }
    public void setTeamB(Team teamB) { this.teamB = teamB; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }

    public Integer getTeamARuns() { return teamARuns; }
    public void setTeamARuns(Integer teamARuns) { this.teamARuns = teamARuns; }

    public Integer getTeamAWickets() { return teamAWickets; }
    public void setTeamAWickets(Integer teamAWickets) { this.teamAWickets = teamAWickets; }

    public Double getTeamAOvers() { return teamAOvers; }
    public void setTeamAOvers(Double teamAOvers) { this.teamAOvers = teamAOvers; }

    public Integer getTeamBRuns() { return teamBRuns; }
    public void setTeamBRuns(Integer teamBRuns) { this.teamBRuns = teamBRuns; }

    public Integer getTeamBWickets() { return teamBWickets; }
    public void setTeamBWickets(Integer teamBWickets) { this.teamBWickets = teamBWickets; }

    public Double getTeamBOvers() { return teamBOvers; }
    public void setTeamBOvers(Double teamBOvers) { this.teamBOvers = teamBOvers; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }

    public String getMatchSummary() { return matchSummary; }
    public void setMatchSummary(String matchSummary) { this.matchSummary = matchSummary; }

    public List<Player> getPlayingXI() { return playingXI; }
    public void setPlayingXI(List<Player> playingXI) { this.playingXI = playingXI; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getCurrentInnings() { return currentInnings; }
    public void setCurrentInnings(Integer currentInnings) { this.currentInnings = currentInnings; }

    public String getCurrentStriker() { return currentStriker; }
    public void setCurrentStriker(String currentStriker) { this.currentStriker = currentStriker; }

    public String getCurrentNonStriker() { return currentNonStriker; }
    public void setCurrentNonStriker(String currentNonStriker) { this.currentNonStriker = currentNonStriker; }

    public String getCurrentBowler() { return currentBowler; }
    public void setCurrentBowler(String currentBowler) { this.currentBowler = currentBowler; }

    public String getCurrentOverDetails() { return currentOverDetails; }
    public void setCurrentOverDetails(String currentOverDetails) { this.currentOverDetails = currentOverDetails; }

    public Integer getTargetScore() { return targetScore; }
    public void setTargetScore(Integer targetScore) { this.targetScore = targetScore; }

    public String getTossResult() { return tossResult; }
    public void setTossResult(String tossResult) { this.tossResult = tossResult; }
}
