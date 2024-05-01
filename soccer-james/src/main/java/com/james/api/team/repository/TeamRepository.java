package com.james.api.team.repository;
import com.james.api.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from teams t order by t.teamName asc") // 1
    List<Team> getTeamByTeamNameOrderByAsc();
    @Query("select t from teams t order by t.teamName desc") // 1
    List<Team> getTeamByTeamNameOrderByDesc();
    @Query("select  t.teamName, p.playerName " +
            "from teams t " +
            "join players p on t.teamId = p.team " +
            "where p.position = 'GK' and t.teamId in ('K02', 'K10')" +
            "order by 1, 2 asc")
    List<Team> getPositionByPlayer(@Param("playerId") Long playerId); // 10

    @Query("select p.playerName, t.teamName, p.height " +
            "from teams t " +
            "join  players p on t.teamId = p.team " +
            "where t.teamId in ('K02', 'K10') and p.height " +
            "between 180 and 183 " +
            "order by p.height,t.teamName,p.playerName asc ")
    List<Team> getTeamByHeightTeamNameAscByHeight(); // 12

//    @Query("SELECT t.teamId, t.teamName, Round(AVG(p.height),2) " +
//            "FROM teams t JOIN players p on t.teamId " +
//            "WHERE IF((p.height= '' or p.height=0),p.height) AND IF((p.weight='' or p.weight='0'), p.weight) AND p.height != '0' " +
//            "GROUP BY p.team " +
//            "HAVING Round(AVG(p.height),2) < (SELECT ROUND(AVG(P.height),2) " +
//            "FROM teams t " +
//            "JOIN players p on (p.team) " +
//            "WHERE t.regionName = '인천') " +
//            "ORDER BY 3 ASC")
//    List<Team> getAvgHeightAndTeamByTeamAndPlayer(); // 19 - 35번째줄 수정필요
    @Query("select t.teamName, p.playerName, p.position, p.backNo " +
            "from teams t join players p on t.teamId " +
            "where p.position = 'MF' " +
            "order by 1,2,3,4 desc")
    List<Team> getPlayersByPosition(); // 20

    @Query("select (select t.teamName " +
            "from teams t " +
            "where t.teamId = p.team) as 소속팀명, p.playerName, p.backNo " +
            "from players p " +
            "order by p.height desc limit 5")
    List<Team> getPlayersByHeight(); // 21



}