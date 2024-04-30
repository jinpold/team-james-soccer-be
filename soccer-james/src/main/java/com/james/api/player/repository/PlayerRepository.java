package com.james.api.player.repository;
import com.james.api.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // -- 002.
    @Query("SELECT DISTINCT p.position FROM players p")
    List<String> getDistinctByPosition();

    // -- 3
    @Query("SELECT CASE WHEN p.position = '' THEN '신입' ELSE p.position END FROM players p")
    List<String> getPositionsWithDefault();

    // -- 4-
    @Query("SELECT p FROM players p WHERE p.team = 'K02' AND p.position = 'GK'")
    List<Player> getGKsByTeamId();

    // -- 4-1-
    @Query("SELECT p FROM players p WHERE p.position = 'GK' AND p.team IN (SELECT t.teamId FROM teams t WHERE t.regionName = '수원')")
    List<Player> getGKsByTeamRegion();

    //-- 5-
    @Query("SELECT p FROM players p WHERE p.team = 'k02' AND p.height >= 170 AND p.playerName LIKE '고%'")
    List<Player> getPlayersByHeightAndNameStartsWith();

    // -- 5-1

    @Query("SELECT p FROM players p "
            + "WHERE p.height >= 170 "
            + "AND p.team IN (SELECT t.teamId FROM teams t WHERE t.regionName = '수원') "
            + "AND p.playerName LIKE '고%'")
    List<Player> getPlayersByTeamRegionHeightAndNameStartsWith();

    // -- 6-
    @Query("SELECT p.playerName FROM players p "
            + "WHERE p.team IN (SELECT t.teamId FROM teams t WHERE t.teamName IN ('삼성블루윙즈', '드래곤즈')) "
            + "AND p.height BETWEEN 170 AND 180 "
            + "AND p.position = 'MF'")
    List<String> getPlayerNamesByTeamNameHeightAndPosition();

    //  --7
    @Query("SELECT p.playerName FROM players p "
            + "WHERE p.position = 'GK' "
            + "AND p.team IN (SELECT t.teamId FROM teams t WHERE t.regionName = '수원')")
    List<String> getGoalkeepersByTeamRegion();

    // --8

    @Query("SELECT p.playerName, COALESCE(p.height, 0), COALESCE(p.weight, 0) "
            + "FROM players p "
            + "WHERE p.team IN (SELECT t.teamId FROM teams t WHERE t.regionName = '서울')"
            + "ORDER BY p.height DESC, p.weight DESC")
    List<String> getPlayersByTeamRegion();

    // --9
//    @Query("SELECT p.playerName, p.position, "
//            + "CONCAT(IFNULL(NULLIF(p.height, ''), '0'), 'cm') AS height, "
//            + "CONCAT(IFNULL(NULLIF(p.weight, ''), '0'), 'kg') AS weight, "
//            + "IFNULL(NULLIF(ROUND(p.weight / (p.height/100 * p.height/100)), ''), 'NONE') AS bmi "
//            + "FROM Player p "
//            + "WHERE p.team.regionName = '서울' "
//            + "ORDER BY p.playerName DESC")
//    List<String> findSeoulPlayersWithBMI();

    // --18
    @Query("SELECT p FROM players p")
    List<Player> getTop5PlayersBy();

    // --18-1

    @Query("SELECT p.playerName FROM players p ORDER BY p.playerName ASC")
    List<String> getTop5OrderByPlayerNames();

    // -- 22

//    @Query("SELECT p FROM players p "
//            + "JOIN (SELECT p2.team, ROUND(AVG(p2.height),2) AS avg "
//            + "      FROM players p2 "
//            + "      WHERE p2.height IS NOT NULL "
//            + "      GROUP BY p2.team) t_avg "
//            + "ON p.team = t_avg.id "
//            + "WHERE p.height IS NOT NULL "
//            + "AND p.height < t_avg.avg "
//            + "ORDER BY p.height DESC")
//    List<Player> findPlayersWithHeightLessThanTeamAverage();


}
