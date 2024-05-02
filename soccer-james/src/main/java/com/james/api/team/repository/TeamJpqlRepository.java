package com.james.api.team.repository;
import com.james.api.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface TeamJpqlRepository extends JpaRepository<Team, Long> {
    //1,12,13,20,21

    //1 -> 정답
    @Query("SELECT new map(t as teamName) FROM teams t ORDER BY t.teamName")
    List<Map<String, Object>> getAllByTeamName();

    // 12 - 키 조건 추가 필요
    @Query("SELECT new map(t.teamName AS teamName, p.playerName AS playerName,p.height as height) FROM teams t JOIN players p ON t.teamId = p.team.teamId WHERE t.teamId in ('K02','k10') ORDER BY t.teamName, p.playerName")
    List<Map<String, Object>> getPlayerByTeamId();

    //13 -> 정답
    @Query("SELECT new map(t.teamName as teamName, p.playerName as playerName) FROM teams t JOIN t.player p WHERE p.position = '' ORDER BY t.teamId, p.playerName")
    List<Map<String, Object>> getTeamByNoPosition();

    //14 -> 정답
    @Query("SELECT new map(t.teamName as hometeamName, s.stadiumName as stadiumName, " +
            "(SELECT t2.teamName FROM teams t2 WHERE sc.awayteamId = t2.teamId) as awayteamName) " +
            "FROM stadiums s " +
            "JOIN schedules sc ON s.stadiumId = sc.stadium.stadiumId " +
            "JOIN teams t ON sc.hometeamId = t.teamId " +
            "WHERE sc.scheDate = '20120317'")
    List<Map<String, Object>> getTeamByDate();

//    //19
//    @Query("select new map(t.teamId as teamId, t.teamName as teamName, round (avg(p.height),2) as avgHeight) "
//            +"from teams t join players p on t.teamId=p.team.teamId where nullif(p.height,'0') and nullif(p.weight,'0') and p.height!='0'"
//            +"group by p.team.teamId having round (avg(p.height),2) < (select round(avg(p.height),2) from teams t join players p where t.regionName='인천')"
//    +"order by 3 asc ")
//    List<Map<String,Object>> getTeamByAvgHeight();

    //19 -> 수정 필요
    @Query("select new map(t.teamId as teamId, t.teamName as teamName, round (avg(cast(p.height as double)),2) as avgHeight) "
            +"from teams t join players p on t.teamId=p.team.teamId where (case when p.height='' then '0' else p.height end ='0') and (case when p.weight='' then '0' else p.weight end ='0') order by 3 asc ")
    List<Map<String,Object>> getTeamByAvgHeight();

    // 21 -> 정답
    @Query("SELECT new map((select t.teamName FROM teams t where t.teamId=p.team.teamId) as teamName,p.playerName as playerName, p.backNo as backNo) from players p join p.team t order by p.height desc limit 5")
    List<Map<String, Object>> getTeamByHeight();
}