package com.cricket.manager.dto;

public class LiveScoreUpdateRequest {
    private Integer teamRuns;
    private Integer teamWickets;
    private Double teamOvers;
    
    private String status;
    private Integer currentInnings;
    private String currentStriker;
    private String currentNonStriker;
    private String currentBowler;
    private String currentOverDetails;
    private Integer targetScore;
    private String tossResult;
    private String matchSummary;

    // Getters and Setters
    public Integer getTeamRuns() { return teamRuns; }
    public void setTeamRuns(Integer teamRuns) { this.teamRuns = teamRuns; }

    public Integer getTeamWickets() { return teamWickets; }
    public void setTeamWickets(Integer teamWickets) { this.teamWickets = teamWickets; }

    public Double getTeamOvers() { return teamOvers; }
    public void setTeamOvers(Double teamOvers) { this.teamOvers = teamOvers; }

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

    public String getMatchSummary() { return matchSummary; }
    public void setMatchSummary(String matchSummary) { this.matchSummary = matchSummary; }
}
