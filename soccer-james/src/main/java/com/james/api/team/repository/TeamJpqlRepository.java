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

    // 19 -> 각 팀의 평균키만 출력됨
    // 인천 유나이티스 팀 평균키 176.59 보다 낮은 팀ID, 팀명, 평균키만 추출
    @Query("SELECT new map(t.teamId AS 팀ID, t.teamName AS 팀명, ROUND(AVG(CAST(p.height AS double)), 2) AS 평균) FROM teams t " +
            "JOIN players p ON t.teamId = p.team.teamId " +
            "WHERE p.height != '' " +
            "GROUP BY t.teamId, t.teamName")
    List<Map<String,Object>> getTeamByAvgHeight();


    // 21 -> 정답
    @Query("SELECT new map((select t.teamName FROM teams t where t.teamId=p.team.teamId) as teamName,p.playerName as playerName, p.backNo as backNo) from players p join p.team t order by p.height desc limit 5")
    List<Map<String, Object>> getTeamByHeight();
}