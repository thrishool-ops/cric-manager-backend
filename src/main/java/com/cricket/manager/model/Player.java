package com.cricket.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String role; // Batsman, Bowler, All-rounder, Wicketkeeper
    private String battingStyle;
    private String bowlingStyle;
    private String country;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Advanced Stats
    private Integer matchesPlayed = 0;
    private Integer runs = 0;
    private Integer wickets = 0;
    
    // Derived or calculated stats can be stored or calculated on the fly. We'll store for performance.
    private Double strikeRate = 0.0;
    private Double battingAverage = 0.0;
    private Double economyRate = 0.0;
    private String bestPerformance;
    
    private String contactDetails;
    private String profileImageUrl;

    public Player() {}

    public Player(String name, Integer age, String role, String battingStyle, String bowlingStyle, String country, Team team) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        this.country = country;
        this.team = team;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getBattingStyle() { return battingStyle; }
    public void setBattingStyle(String battingStyle) { this.battingStyle = battingStyle; }
    
    public String getBowlingStyle() { return bowlingStyle; }
    public void setBowlingStyle(String bowlingStyle) { this.bowlingStyle = bowlingStyle; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    
    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }
    
    public Integer getRuns() { return runs; }
    public void setRuns(Integer runs) { this.runs = runs; }
    
    public Integer getWickets() { return wickets; }
    public void setWickets(Integer wickets) { this.wickets = wickets; }
    
    public Double getStrikeRate() { return strikeRate; }
    public void setStrikeRate(Double strikeRate) { this.strikeRate = strikeRate; }
    
    public Double getBattingAverage() { return battingAverage; }
    public void setBattingAverage(Double battingAverage) { this.battingAverage = battingAverage; }
    
    public Double getEconomyRate() { return economyRate; }
    public void setEconomyRate(Double economyRate) { this.economyRate = economyRate; }
    
    public String getBestPerformance() { return bestPerformance; }
    public void setBestPerformance(String bestPerformance) { this.bestPerformance = bestPerformance; }
    
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
    
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
}
