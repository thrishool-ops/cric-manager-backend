package com.cricket.manager.repository;

import com.cricket.manager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
    List<Player> findByRole(String role);
    List<Player> findByNameContainingIgnoreCase(String name);
    
    // Custom query methods for leaderboards could go here, e.g.,
    List<Player> findTop10ByOrderByRunsDesc();
    List<Player> findTop10ByOrderByWicketsDesc();
}
