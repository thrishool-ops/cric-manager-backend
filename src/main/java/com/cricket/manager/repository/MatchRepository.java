package com.cricket.manager.repository;

import com.cricket.manager.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findTop5ByOrderByMatchDateDescIdDesc();
    List<Match> findByTeamAIdOrTeamBId(Long teamAId, Long teamBId);
}
