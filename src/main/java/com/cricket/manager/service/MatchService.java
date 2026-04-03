package com.cricket.manager.service;

import com.cricket.manager.model.Match;
import com.cricket.manager.repository.MatchRepository;
import com.cricket.manager.dto.LiveScoreUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public List<Match> getRecentMatches() {
        return matchRepository.findTop5ByOrderByMatchDateDescIdDesc();
    }

    public List<Match> getMatchesByTeam(Long teamId) {
        return matchRepository.findByTeamAIdOrTeamBId(teamId, teamId);
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Long id, Match matchDetails) {
        return matchRepository.findById(id).map(match -> {
            match.setTeamA(matchDetails.getTeamA());
            match.setTeamB(matchDetails.getTeamB());
            match.setTournament(matchDetails.getTournament());
            match.setVenue(matchDetails.getVenue());
            match.setMatchDate(matchDetails.getMatchDate());
            match.setMatchType(matchDetails.getMatchType());
            
            // Scorecard
            match.setTeamARuns(matchDetails.getTeamARuns());
            match.setTeamAWickets(matchDetails.getTeamAWickets());
            match.setTeamAOvers(matchDetails.getTeamAOvers());
            match.setTeamBRuns(matchDetails.getTeamBRuns());
            match.setTeamBWickets(matchDetails.getTeamBWickets());
            match.setTeamBOvers(matchDetails.getTeamBOvers());
            match.setWinner(matchDetails.getWinner());
            match.setMatchSummary(matchDetails.getMatchSummary());
            
            // New Live scoring fields
            if (matchDetails.getStatus() != null) match.setStatus(matchDetails.getStatus());
            if (matchDetails.getCurrentInnings() != null) match.setCurrentInnings(matchDetails.getCurrentInnings());
            if (matchDetails.getCurrentStriker() != null) match.setCurrentStriker(matchDetails.getCurrentStriker());
            if (matchDetails.getCurrentNonStriker() != null) match.setCurrentNonStriker(matchDetails.getCurrentNonStriker());
            if (matchDetails.getCurrentBowler() != null) match.setCurrentBowler(matchDetails.getCurrentBowler());
            if (matchDetails.getCurrentOverDetails() != null) match.setCurrentOverDetails(matchDetails.getCurrentOverDetails());
            if (matchDetails.getTargetScore() != null) match.setTargetScore(matchDetails.getTargetScore());
            if (matchDetails.getTossResult() != null) match.setTossResult(matchDetails.getTossResult());
            
            match.setPlayingXI(matchDetails.getPlayingXI());

            return matchRepository.save(match);
        }).orElseThrow(() -> new RuntimeException("Match not found"));
    }

    public Match updateLiveScore(Long id, LiveScoreUpdateRequest request) {
        return matchRepository.findById(id).map(match -> {
            Integer innings = request.getCurrentInnings() != null ? request.getCurrentInnings() : match.getCurrentInnings();
            
            if (innings != null && innings == 1) {
                if (request.getTeamRuns() != null) match.setTeamARuns(request.getTeamRuns());
                if (request.getTeamWickets() != null) match.setTeamAWickets(request.getTeamWickets());
                if (request.getTeamOvers() != null) match.setTeamAOvers(request.getTeamOvers());
            } else if (innings != null && innings == 2) {
                if (request.getTeamRuns() != null) match.setTeamBRuns(request.getTeamRuns());
                if (request.getTeamWickets() != null) match.setTeamBWickets(request.getTeamWickets());
                if (request.getTeamOvers() != null) match.setTeamBOvers(request.getTeamOvers());
            }

            if (request.getStatus() != null) match.setStatus(request.getStatus());
            if (request.getCurrentInnings() != null) match.setCurrentInnings(request.getCurrentInnings());
            if (request.getCurrentStriker() != null) match.setCurrentStriker(request.getCurrentStriker());
            if (request.getCurrentNonStriker() != null) match.setCurrentNonStriker(request.getCurrentNonStriker());
            if (request.getCurrentBowler() != null) match.setCurrentBowler(request.getCurrentBowler());
            if (request.getCurrentOverDetails() != null) match.setCurrentOverDetails(request.getCurrentOverDetails());
            if (request.getTargetScore() != null) match.setTargetScore(request.getTargetScore());
            if (request.getTossResult() != null) match.setTossResult(request.getTossResult());
            if (request.getMatchSummary() != null) match.setMatchSummary(request.getMatchSummary());

            return matchRepository.save(match);
        }).orElseThrow(() -> new RuntimeException("Match not found"));
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
