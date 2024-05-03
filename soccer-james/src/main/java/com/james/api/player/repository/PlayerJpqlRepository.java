package com.james.api.player.repository;
import com.james.api.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface PlayerJpqlRepository extends JpaRepository<Player , Long> {


    //2번  O 파라미터 X
    @Query("SELECT DISTINCT new map(p.position AS position) FROM players p")
    List<Map<String, Object>> getDistinctByPosition();

    //3번  O 파라미터 X
    @Query("SELECT DISTINCT new map (IFNULL(NULLIF(p.position, ''), '신입') AS position) FROM players p ")
    List<Map<String, Object>> getDistinctByPositionNotNull();

    //4번  X - 확장성을 위해 teamName으로 바꿔 줄 필요성이 있음.
    @Query("SELECT new map (p.team.teamId AS teamId , p.position AS position ) " +
            "FROM players p where p.position = :position AND p.team.teamId = :teamId1")
    List<Map<String ,Object>> getOnByPositionAndTeamId(@Param("teamId1") String teamId1, @Param("position") String position);

    //4-1번 O
    @Query("SELECT new map( p.position AS position ,p.team.teamId ) FROM players p WHERE p.position = 'GK'" +
            "                       AND p.team.teamId =\n" +
            "                           (SELECT t.teamId from teams t where t.regionName = :regionName)")
    List<Map<String , Object>> getOnByPositionAndTeamId2(@Param("regionName") String regionName);

    //5번 O

    @Query("SELECT new map (p.team.teamId AS teamId, p.height as height , p.playerName)" +
            " FROM players p WHERE p.height != '' "+
            "  AND p.playerName LIKE '고%'" +
            "  AND p.team.teamId = " +
            "      (SELECT t.teamId FROM teams t where t.regionName = :regionName)")
    List<Map<String , Object>> getOnByPositionAndTeamIdAndHeight(@Param("regionName") String regionName);

    // 5-1번 X
    @Query("SELECT new map (p.team.teamId , p.height , p.playerName ) FROM players p JOIN teams t on t.teamId = p.team.teamId\n" +
            "         WHERE p.height = :height" +
            "           AND p.playerName LIKE concat(:playerName,'%')" +
            "           AND t.regionName= :regionName")
    List<Map<String , Object>> getOnByPositionAndTeamIdAndHeight2
    (@Param("height") String height, @Param("playerName") String playerName, @Param("regionName") String regionName);

    // 6번 X
    @Query("SELECT new map (p.playerName,p.position,p.team.teamId) FROM players p WHERE p.position = :position\n" +
            "                              AND p.height BETWEEN :height1 AND :height2" +
            "                              AND p.team.teamId IN" +
            "                                  (SELECT t.teamId from teams t WHERE t.teamName IN (:teamName1, :teamName2))")
    List<Map<String , Object>> getOnByPositionAndHeightAndTeamId
    (@Param("teamName1") String teamName1, @Param("teamName2") String teamName2, @Param("position") String position,
     @Param("height1") String height1, @Param("height2") String height2);


    //7번 O

    @Query("SELECT new map (p.position AS position, p.team.teamId as teamId )FROM players p WHERE p.position = 'GK'" +
            "AND p.team.teamId = (SELECT t.teamId FROM teams t WHERE t.regionName = :regionName)")
    List<Map<String, Object>> getPositionAndeRegion(@Param("regionName") String regionName);



    //8번  수정 필요
    @Query("SELECT new map (p.playerName AS name," +
            "       IFNULL(NULLIF(p.height, ''), '0') AS height," +
            "       IFNULL(NULLIF(p.weight, ''), '0') AS weight) FROM players p WHERE p.team.teamId =" +
            "                                                              (SELECT t.teamId FROM teams t WHERE t.regionName = :regionName)")
    List<Map<String ,Object>> getOnByHeightAndWeight(@Param("regionName") String regionName);

    //9번
    @Query("SELECT new map (p.playerName AS playerName, p.position AS position, " +
            "COALESCE(cast(p.height AS DOUBLE), '0') AS height, " +
            "COALESCE(cast(p.weight AS DOUBLE), '0') AS weight, " +
            "CASE WHEN p.height IS NOT NULL AND p.weight IS NOT NULL THEN ROUND((CAST(p.weight AS double) / " +
            "(CAST(p.height AS DOUBLE) / 100) * (CAST(p.height AS DOUBLE ) / 100)), 2) ELSE 0 END AS BMI) " +
            "FROM players p JOIN teams t ON p.team.teamId = t.teamId WHERE t.regionName = '서울'")
    List<Map<String, Object>> getPlayerInfoByRegion();



    //10번 수정필요
    @Query("SELECT new map (p.position AS position , p.team.teamId AS teamId, p.playerName AS playerName " +
            ")FROM players p WHERE p.position = :position AND p.team.teamId IN (:teamId1, :teamId2)" +
            "ORDER BY" +
            "    (SELECT t.teamName" +
            "     FROM teams t" +
            "     WHERE t.teamId = p.team.teamId)," +
            "    p.playerName")
    List<Map<String , Object>> getOnByPositionAndTeamId10(@Param("position") String position,
                                                          @Param("teamId1") String teamId1, @Param("teamId2") String teamId2);


    //18번  - Limit 추가 필요
    @Query(value = "SELECT new map (p.team.teamId , p.playerName , p.position , p.name , p.backNo , p.nickname , p.solar , p.ePlayerName) FROM players p ")
    List<Map<String , Object>> getOnCountAll(@Param("limit") Integer limit);


    //20번
    @Query("SELECT new MAP ((SELECT t.teamName\n" +
            "        FROM teams t\n" +
            "        WHERE t.teamId = p.team.teamId) AS teamId,\n" +
            "    p.playerName AS playerName,\n" +
            "    p.backNo AS backNo\n" +
            ")FROM players p\n" +
            "WHERE p.position = :position")
    List<Map<String , Object>> getByOnPositionAndTeamId20(@Param("position") String position);

    // 21번
    @Query("SELECT new MAP ((SELECT t.teamName\n" +
            "        FROM teams t\n" +
            "        WHERE t.teamId = p.team.teamId),\n" +
            "    p.playerName,\n" +
            "    p.backNo\n" +
            ")FROM players p\n" +
            "ORDER BY p.height\n" )
    List<Map<String , Object>> getOnByPositionAndTeamId21(@Param("teamId") String teamId);

    // 22번

    @Query(value = "SELECT new  map (p.height AS height , p.team.teamId AS teamId ," +
            "p.ePlayerName AS ePlayerName,p.playerId AS playerId)" +
            "FROM players p WHERE cast(p.height AS double) < cast( (SELECT  ROUND(AVG(cast( tp.height AS double )), 2)" +
            "FROM players tp WHERE p.team.teamId = tp.team.teamId) AS double )")
    List<Map<String, Object>> getHeightAndTeamId();

}