package com.james.api.player.repository;
import com.james.api.player.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface PlayerJpqlRepository extends JpaRepository<Player , Long> {


    //2번
    @Query("SELECT DISTINCT new map(p.position AS POSITION) FROM players p")
    List<Map<String, Object>> getDistinctByPosition();

    //3번
    @Query("SELECT DISTINCT new map (IFNULL(NULLIF(p.position, ''), '신입') AS POSITION) FROM players p ")
    List<Map<String, Object>> getDistinctByPositionNotNull();

    //4번
    @Query("SELECT new map (p.team.teamId AS teamId , p.position AS POSITION ) FROM players p where p.position = 'GK' AND p.team.teamId = 'k02'")
    List<Map<String ,Object>> getOnByPositionAndTeamId();

    //4-1번
    @Query(value = "SELECT new map( p.position AS POSITION ,p.team.teamId ) FROM players p WHERE p.position = 'GK'\n" +
            "                       AND p.team.teamId =\n" +
            "                           (SELECT t.teamId from teams t where t.regionName = '수원')")
    List<Map<String , Object>> getOnByPositionAndTeamId2();

    //5번

    @Query("SELECT new map (p.team.teamId , p.height as height , p.playerName) FROM players p\n" +
            "WHERE p.height != ''     \n"  +
            "  AND p.playerName LIKE '고%'\n" +
            "  AND p.team.teamId =\n" +
            "      (SELECT t.teamId FROM teams t WHERE t.regionName = '수원')")
    List<Map<String , Object>> getOnByPositionAndTeamIdAndHeight();

    // 5-1번
    @Query("SELECT new map (p.team.teamId , p.height , p.playerName ) FROM players p JOIN teams t on t.teamId = p.team.teamId\n" +
            "         WHERE p.height = '170'\n" +
            "           AND p.playerName LIKE '고%'\n" +
            "           AND t.regionName= '수원'")
    List<Map<String , Object>> getOnByPositionAndTeamIdAndHeight2();

    // 6번
    @Query("SELECT new map (p.playerName,p.position,p.team.teamId) FROM players p WHERE p.position = 'MF'\n" +
//            "                              AND p.height BETWEEN 170 AND 180\n" +
            "                              AND p.team.teamId IN\n" +
            "                                  (SELECT t.teamId from teams t WHERE t.teamName IN ('삼성블루윙즈', '드래곤즈'))")
    List<Map<String , Object>> getOnByPositionAndHeightAndTeamId();

    //8번
    @Query("SELECT new map (p.team.teamId as 플레이어팀ID , t.teamId as team팀 , t.regionName AS 연고지, p.position AS POSITION) FROM players p JOIN teams t on t.teamId = p.team.teamId\n" +
            "                          where p.position = 'GK' AND t.regionName = '수원'")
    List<Map<String ,Object>> getOnByPositionAndTeamId7();

    //9번
    @Query("SELECT new map (p.playerName AS 이름,\n" +
            "       IFNULL(NULLIF(p.height, ''), '0') AS 키,\n" +
            "       IFNULL(NULLIF(p.weight, ''), '0') AS 몸무게) FROM players p WHERE p.team.teamId =\n" +
            "                                                              (SELECT t.teamId FROM teams t WHERE t.regionName = '서울')")
    List<Map<String ,Object>> getOnByHeightAndWeight();

    //10번
    @Query("SELECT new map (p.position AS POSITION , p.team.teamId AS TEAMID, p.playerName AS PLAYERNAME )FROM players p\n" +
            "WHERE p.position = 'GK'\n" +
            "AND p.team.teamId IN ('K02', 'K10')\n" +
            "ORDER BY\n" +
            "    (SELECT t.teamName\n" +
            "     FROM teams t\n" +
            "     WHERE t.teamId = p.team.teamId),\n" +
            "    p.playerName")
    List<Map<String , Object>> getOnByPositionAndTeamId10();


    //18번
    @Query("SELECT new map (p.team.teamId , p.playerName , p.position , p.name , p.backNo , p.nickname , p.solar , p.ePlayerName) FROM players p ")
    List<Map<String , Object>> getOnCountAll();

    //20번
    @Query("SELECT new MAP ((SELECT t.teamName\n" +
            "        FROM teams t\n" +
            "        WHERE t.teamId = p.team.teamId) AS 소속팀,\n" +
            "    p.playerName AS 선수명,\n" +
            "    p.backNo AS 등번호\n" +
            ")FROM players p\n" +
            "WHERE p.position = 'MF'")
    List<Map<String , Object>> getByOnPositionAndTeamId20();

    // 21번
    @Query("SELECT new MAP ((SELECT t.teamName\n" +
            "        FROM teams t\n" +
            "        WHERE t.teamId = p.team.teamId),\n" +
            "    p.playerName,\n" +
            "    p.backNo\n" +
            ")FROM players p\n" +
            "ORDER BY p.height\n" )
    List<Map<String , Object>> getOnByPositionAndTeamId21();


}