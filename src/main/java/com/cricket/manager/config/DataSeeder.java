package com.cricket.manager.config;

import com.cricket.manager.model.Match;
import com.cricket.manager.model.Player;
import com.cricket.manager.model.Team;
import com.cricket.manager.model.Tournament;
import com.cricket.manager.repository.MatchRepository;
import com.cricket.manager.repository.PlayerRepository;
import com.cricket.manager.repository.TeamRepository;
import com.cricket.manager.repository.TournamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DataSeeder {

    private final Random random = new Random();

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepo, PlayerRepository playerRepo, 
                                   MatchRepository matchRepo, TournamentRepository tournamentRepo) {
        return args -> {
            seedData(teamRepo, playerRepo, matchRepo, tournamentRepo);
        };
    }

    public void seedData(TeamRepository teamRepo, PlayerRepository playerRepo, 
                         MatchRepository matchRepo, TournamentRepository tournamentRepo) {
        // Only seed if database is empty to prevent duplicates on manual trigger
        if (teamRepo.count() == 0) {
            System.out.println("Seeding Database with Realistic Cricket Data...");

            // 1. Create Teams
            List<Team> teams = createTeams(teamRepo);

            // 2. Create 100 Players (Approx 12 per team)
            List<Player> allPlayers = createPlayers(playerRepo, teams);

            // 3. Create a Tournament
            Tournament t = new Tournament("Global Premier League 2026", "Multiple Venues", 
                    LocalDate.now().minusDays(30), LocalDate.now().plusDays(15), "ONGOING");
            tournamentRepo.save(t);

            // 4. Create 30 Matches
            createMatches(matchRepo, teams, allPlayers, t);

            System.out.println("Database Seeding Completed.");
        } else {
            System.out.println("Database already contains data. Skipping Seeding.");
        }
    }

    private List<Team> createTeams(TeamRepository teamRepo) {
        List<Team> teams = Arrays.asList(
            new Team("Mumbai Indians", "MI", "Mahela Jayawardene", "Wankhede Stadium"),
            new Team("Chennai Super Kings", "CSK", "Stephen Fleming", "MA Chidambaram Stadium"),
            new Team("Royal Challengers Bangalore", "RCB", "Andy Flower", "M. Chinnaswamy Stadium"),
            new Team("Kolkata Knight Riders", "KKR", "Chandrakant Pandit", "Eden Gardens"),
            new Team("Delhi Capitals", "DC", "Ricky Ponting", "Arun Jaitley Stadium"),
            new Team("Sunrisers Hyderabad", "SRH", "Daniel Vettori", "Rajiv Gandhi Intl Stadium"),
            new Team("Rajasthan Royals", "RR", "Kumar Sangakkara", "Sawai Mansingh Stadium"),
            new Team("Gujarat Titans", "GT", "Ashish Nehra", "Narendra Modi Stadium")
        );
        return teamRepo.saveAll(teams);
    }

    private List<Player> createPlayers(PlayerRepository playerRepo, List<Team> teams) {
        List<Player> players = new ArrayList<>();
        
        String[] firstNames = {"Virat", "MS", "Rohit", "Surya", "Jasprit", "Ravindra", "Hardik", "KL", "Shubman", "Rishabh", 
                               "David", "Glen", "Pat", "Mitchell", "Trent", "Kane", "Rashid", "Faf", "Kagiso", "Quinton"};
        String[] lastNames = {"Kohli", "Dhoni", "Sharma", "Yadav", "Bumrah", "Jadeja", "Pandya", "Rahul", "Gill", "Pant",
                              "Warner", "Maxwell", "Cummins", "Starc", "Boult", "Williamson", "Khan", "du Plessis", "Rabada", "de Kock"};
        String[] roles = {"Batsman", "Bowler", "All-rounder", "Wicketkeeper"};
        String[] battingStyles = {"Right-hand bat", "Left-hand bat"};
        String[] bowlingStyles = {"Right-arm fast", "Right-arm medium", "Right-arm offbreak", "Right-arm legbreak", 
                                  "Left-arm fast", "Left-arm orthodox", "None"};

        // Generate 100 players
        for (int i = 0; i < 100; i++) {
            String name = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)] + " " + (i+1);
            Team team = teams.get(i % teams.size());
            String role = roles[random.nextInt(roles.length)];
            
            String batting = battingStyles[random.nextInt(battingStyles.length)];
            String bowling = role.equals("Batsman") || role.equals("Wicketkeeper") ? "None" : bowlingStyles[random.nextInt(bowlingStyles.length - 1)];
            
            Player p = new Player(name, 20 + random.nextInt(18), role, batting, bowling, "International", team);
            
            // Random realistic stats
            int matches = 10 + random.nextInt(140);
            p.setMatchesPlayed(matches);
            
            if (role.equals("Batsman") || role.equals("All-rounder") || role.equals("Wicketkeeper")) {
                p.setRuns(matches * (15 + random.nextInt(35)));
                p.setStrikeRate(110.0 + random.nextDouble() * 50);
                p.setBattingAverage(20.0 + random.nextDouble() * 30);
                p.setBestPerformance((50 + random.nextInt(80)) + "* (" + (30 + random.nextInt(40)) + ")");
            } else {
                p.setRuns(matches * random.nextInt(10));
                p.setBattingAverage(5.0 + random.nextDouble() * 10);
            }
            
            if (role.equals("Bowler") || role.equals("All-rounder")) {
                p.setWickets(matches + random.nextInt(50) - 20);
                if (p.getWickets() < 0) p.setWickets(0);
                p.setEconomyRate(6.5 + random.nextDouble() * 3);
                p.setBestPerformance((3 + random.nextInt(4)) + "/" + (15 + random.nextInt(30)));
            }
            
            players.add(p);
        }
        
        return playerRepo.saveAll(players);
    }

    private void createMatches(MatchRepository matchRepo, List<Team> teams, List<Player> players, Tournament t) {
        List<Match> matches = new ArrayList<>();
        
        String[] matchTypes = {"T20", "ODI"};
        
        for (int i = 0; i < 40; i++) {
            Team teamA = teams.get(random.nextInt(teams.size()));
            Team teamB;
            do {
                teamB = teams.get(random.nextInt(teams.size()));
            } while (teamA.getId().equals(teamB.getId()));
            
            LocalDate pastDate = LocalDate.now().minusDays(random.nextInt(30));
            Match m = new Match(teamA, teamB, teamA.getHomeVenue(), pastDate, matchTypes[random.nextInt(matchTypes.length)]);
            m.setTournament(t);
            
            // Generate realistic scores
            int aRuns = 120 + random.nextInt(100);
            int aWickets = random.nextInt(11);
            m.setTeamARuns(aRuns);
            m.setTeamAWickets(aWickets);
            m.setTeamAOvers(20.0);
            
            int bRuns = 120 + random.nextInt(100);
            int bWickets = random.nextInt(11);
            m.setTeamBRuns(bRuns);
            m.setTeamBWickets(bWickets);
            
            if (aRuns > bRuns) {
                m.setWinner(teamA.getShortName());
                m.setTeamBOvers(20.0);
                m.setMatchSummary(teamA.getName() + " won by " + (aRuns - bRuns) + " runs.");
            } else if (bRuns > aRuns) {
                m.setWinner(teamB.getShortName());
                m.setTeamBOvers(15.0 + random.nextDouble() * 4.9);
                m.setMatchSummary(teamB.getName() + " won by " + (10 - bWickets) + " wickets.");
            } else {
                m.setWinner("Tie");
                m.setTeamBOvers(20.0);
                m.setMatchSummary("Match Tied.");
            }
            
            // Randomly select 11 players from Team A and 11 from Team B for the Playing XI
            final Team finalTeamA = teamA;
            final Team finalTeamB = teamB;
            List<Player> teamAPlayers = players.stream().filter(p -> p.getTeam().getId().equals(finalTeamA.getId())).limit(11).toList();
            List<Player> teamBPlayers = players.stream().filter(p -> p.getTeam().getId().equals(finalTeamB.getId())).limit(11).toList();
            
            List<Player> playingXI = new ArrayList<>();
            playingXI.addAll(teamAPlayers);
            playingXI.addAll(teamBPlayers);
            m.setPlayingXI(playingXI);
            
            matches.add(m);
        }
        
        matchRepo.saveAll(matches);
    }
}
