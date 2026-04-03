package com.cricket.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String shortName; // e.g., IND, CSK
    private String coach;
    private String homeVenue;
    
    // Default constructor
    public Team() {}
    
    public Team(String name, String shortName, String coach, String homeVenue) {
        this.name = name;
        this.shortName = shortName;
        this.coach = coach;
        this.homeVenue = homeVenue;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    
    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }
    
    public String getHomeVenue() { return homeVenue; }
    public void setHomeVenue(String homeVenue) { this.homeVenue = homeVenue; }
}
