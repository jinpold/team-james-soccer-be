package com.james.api.stadium.repository;

import com.james.api.stadium.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("hiding")
@Repository
public interface StadiumJpqlRepository extends JpaRepository<Stadium,Long> {
    //problem 11
    @Query("select distinct new map(s.stadiumName as stadiumName, concat(:regionName, '[ ]',t.teamName) as teamName) " +
            "from stadiums s join teams t on s.stadiumId = t.stadium.stadiumId " +
            "where t.regionName = :regionName and s.stadiumName like concat(:regionName,'%')")
    List<Map<String, Object>> getStadiumNameWithTeam(@Param("regionName") String regionName);

    //problem 14
    @Query("select distinct new map(t.teamName as teamName, s.stadiumName as stadiumName, " +
            "(select distinct t2.teamName from teams t2 where sc.awayteamId = t2.teamId) as awayteamName) " +
            "from stadiums s " +
            "join teams t on s.stadiumId = t.stadium.stadiumId " +
            "join schedules sc on s.stadiumId = sc.stadium.stadiumId " +
            "where sc.scheDate = :date")
    List<Map<String, Object>> getStadiumAndTeamAndSchedule(@Param("date") String date);

    //problem 15
    @Query("SELECT distinct new map(p.playerName as playerName, p.position as position, concat(t.teamName, ' ', t.regionName) as teamName, " +
            "s.stadiumName as stadiumName, sch.scheDate as scheDate)" +
            "FROM stadiums s " +
            "JOIN schedules sch ON s.stadiumId = sch.stadium.stadiumId " +
            "JOIN teams t ON s.stadiumId = t.stadium.stadiumId " +
            "JOIN players p ON t.teamId = p.team.teamId " +
            "WHERE p.position = :position AND t.teamName = :teamName AND sch.scheDate = :date")
    List<Map<String, Object>> getPohangSteelersGk(@Param("date") String date,@Param("teamName") String teamName,@Param("position") String position);


    //problem 16
    @Query("SELECT new map(s.stadiumName as stadiumName, sch.scheDate as scheDate, home.teamName as hometeamName, away.teamName as awayteamName) " +
            "FROM stadiums s " +
            "JOIN schedules sch on s.stadiumId = sch.stadium.stadiumId " +
            "join teams home ON home.teamId = sch.hometeamId " +
            "JOIN teams away ON away.teamId = sch.awayteamId " +
            "WHERE (sch.homeScore - sch.awayScore) >= cast(:score as int)")
    List<Map<String, Object>> getHomeTeamWin(@Param("score") String score);

    //problem 17
    @Query("SELECT distinct new map(s.stadiumName as stadiumName, " +
            "(SELECT distinct t2.teamName FROM teams t2 WHERE t2.stadium.stadiumId = s.stadiumId) as teamName) " +
            "FROM stadiums s " +
            "left join teams t on s.stadiumId = t.stadium.stadiumId")
    List<Map<String, Object>> getNoHomeTeam();
}