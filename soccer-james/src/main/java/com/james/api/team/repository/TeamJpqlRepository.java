package com.james.api.team.repository;
import com.james.api.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface TeamJpqlRepository extends JpaRepository<Team, Long> {

    //1
    @Query("SELECT new map(t as team) FROM teams t ORDER BY t.teamId")
    List<Map<String, Object>> getAllByTeamName();

    // 10
    @Query("SELECT new map(t as team) FROM teams t JOIN players p ON t.teamId = p.team WHERE t.teamId IN('K02', 'K10') AND p.position = 'GK' ORDER BY t.teamId, p.playerName")
    List<Map<String, Object>> getPlayerOrderBy();

    // 12

    @Query("SELECT new map(t.teamName AS teamName, p.playerName AS playerName) FROM teams t JOIN players p ON t.teamId = p.team.teamId WHERE p.position = \'\' ORDER BY t.teamName, p.playerName")
    List<Map<String, Object>> getPlayerByTeamId();

    // 13

    @Query("SELECT new map(t.teamId , p.playerName) FROM teams t JOIN players p ON t.teamId = p.team WHERE p.position = '' ORDER BY t.teamId, p.playerName")
    List<Map<String, Object>> getTeamByNoPosition();

    // 14

    @Query("SELECT new map(t.teamId as HOME_TEAM, s.stadiumName, (SELECT t.teamId FROM teams t WHERE sc.awayteamId = t.teamId) as AWAY_TEAM) FROM stadiums s JOIN schedules sc ON s.stadiumId = sc.stadium  JOIN teams t ON s.stadiumId = t.stadium WHERE sc.scheDate = '20120317'")
    List<Map<String, Object>> getTeamByDate();

    // 22

    @Query("SELECT new map(t as team) FROM teams t ORDER BY t.teamId")
    List<Map<String, Object>> getTeamByHeight();
}