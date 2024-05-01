package com.james.api.team.repository;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.james.api.team.model.Team;

public interface TeamJpqlRepository extends JpaRepository<Team, Long> {
    String dto = " new map(" +
            "t.id AS id, t.teamId AS teamId, t.regionName AS regionName, t.teamName AS teamName, " +
            "t.eTeamName AS eTeamName, t.origYyyy AS origYyyy, t.zipCode1 AS zipCode1, t.zipCode2 AS zipCode2, " +
            "t.address AS address, t.ddd AS ddd, t.tel AS tel, t.fax AS fax, t.homepage AS homepage, " +
            "t.owner AS owner, t.stadiumId.stadiumId as stadiumId) from team t ";


    @Query(value = "SELECT " + dto + "ORDER BY t.teamName ASC")
    List<Map<String, Object>> getTeamsOrderByTeamName();


    @Query(value =
            "SELECT new map(t.teamName AS teamName, p.playerName AS playerName)" +
                    "FROM teams t JOIN players p ON t.teamId = p.team.teamId " +
                    "WHERE p.position = \'\'" +
                    "ORDER BY t.teamName, p.playerName")
    List<Map<String, Object>> getNotSelectedPostion();

    @Query(value =
            "SELECT new map(" +
                    "p.id AS id, p.playerId AS playerId, p.name AS name, p.team.teamId AS teamId, p.playerName AS playerName, " +
                    "p.ePlayerName AS ePlayerName, p.nickname AS nickname, p.joinYyyy AS joinYyyy, p.position AS position, " +
                    "p.backNo AS backNo, p.nation AS nation, p.birthDate AS birthDate, p.solar AS solar, p.height AS height, p.weight AS weight) FROM teams t " +
                    "JOIN players p ON t.teamId = p.team.teamId " +
                    "WHERE p.height != \'\' " +
                    "ORDER BY p.height, p.playerName")
    List<Map<String, Object>> getTeamsByteamName();

    @Query(value =
            "SELECT new map(t.teamId AS 팀ID, t.teamName AS 팀명, ROUND(AVG(CAST(p.height AS double)), 2) AS 평균) FROM teams t " +
                    "JOIN players p ON t.teamId = p.team.teamId " +
                    "GROUP BY t.teamId, t.teamName")
    List<Map<String, Object>> getHeightAvgByTeam();

}