package com.james.api.stadium.repository;
import com.james.api.stadium.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    @Query("select distinct new map(s.stadiumName as 홈구장, concat(t.regionName, '[ ]',t.teamName) as 팀명) " +
            "from stadiums s join teams t on s.stadiumId = t.stadium.stadiumId " +
            "where t.regionName = '수원' and s.stadiumName like '수원%'")
    List<Map<String, Object>> getStadiumNameWithTeam();

    @Query("select distinct new map(t.teamName as 팀이름, s.stadiumName as 스타디움, " +
            "(select distinct t2.teamName from teams t2 where sc.awayteamId = t2.teamId) as 어웨이팀) " +
            "from stadiums s " +
            "join teams t on s.stadiumId = t.stadium.stadiumId " +
            "join schedules sc on s.stadiumId = sc.stadium.stadiumId " +
            "where sc.scheDate = '20120317'")
    List<Map<String, Object>> getStadiumAndTeamAndSchedule();


    @Query("SELECT distinct new map(p.playerName as 이름, p.position as 포지션, concat(t.teamName, ' ', t.regionName) as 팀명, " +
            "s.stadiumName as 스타디움, sch.scheDate as 경기날짜)" +
            "FROM stadiums s " +
            "JOIN schedules sch ON s.stadiumId = sch.stadium.stadiumId " +
            "JOIN teams t ON s.stadiumId = t.stadium.stadiumId " +
            "JOIN players p ON t.teamId = p.team.teamId " +
            "WHERE p.position = 'GK' AND t.teamName = '스틸러스' AND sch.scheDate = '20120317'")
    List<Map<String, Object>> getPohangSteelersGk();


    @Query("SELECT new map(s.stadiumName as 스타디움, sch.scheDate as 경기일정, home.teamName as 홈팀명, away.teamName as 어웨이팀명) " +
            "FROM stadiums s " +
            "JOIN schedules sch on s.stadiumId = sch.stadium.stadiumId " +
            "join teams home ON home.teamId = sch.hometeamId " +
            "JOIN teams away ON away.teamId = sch.awayteamId " +
            "WHERE (sch.homeScore - sch.awayScore) >= 3")
    List<Map<String, Object>> getHomeTeamWin();

    @Query("SELECT distinct new map(s.stadiumName as 스타디움, " +
            "(SELECT distinct t2.teamName FROM teams t2 WHERE t2.stadium.stadiumId = s.stadiumId) as 팀명) " +
            "FROM stadiums s " +
            "left join teams t on s.stadiumId = t.stadium.stadiumId")
    List<Map<String, Object>> getNoHomeTeam();

}
