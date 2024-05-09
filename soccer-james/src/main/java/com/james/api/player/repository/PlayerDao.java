package com.james.api.player.repository;

import com.james.api.player.model.PlayerDto;

import java.util.List;
import java.util.Map;




public interface PlayerDao { // 인터페이스  서비스

    // total row
    Long countAllPlayers();

    //All
    List<PlayerDto> getAllPlayersDSL();

    //2번  O 파라미터 X

    List<PlayerDto> getDistinctByPositionDSL();

    //3번  O 파라미터 X
    List<?> getDistinctByPositionNotNullDSL();

    //4번  X - 확장성을 위해 teamName으로 바꿔 줄 필요성이 있음.
    List<?> getOnByPositionAndTeamIdDSL(String position, String teamName1);

    //4-1번 O

    List<String> getOnByPositionAndTeamId2DSL();

    //5번 O
    List<?> getOnByPositionAndTeamIdAndHeightDSL(String regionName1);

    // 5-1번 X
    List<?> getOnByPositionAndTeamIdAndHeight2DSL(String playerName, String regionName, String height);

    // 6번 X
    List<?> getOnByPositionAndHeightAndTeamIdDSL(String position, String height1, String height2, String teamName1, String teamName2);


    //7번 O
    List<PlayerDto> getPositionAndRegionDSL();

    //8번  수정 필요
    List<Map<String, String>> getOnByHeightAndWeightDSL(String regionName);

    //9번
    List<?> getPlayerInfoByRegionDSL();

    //10번 수정필요
    List<?> getOnByPositionAndTeamId10DSL();


    //18번  - Limit 추가 필요
    List<?> getOnCountAllDSL();

    //20번
    List<?> getByOnPositionAndTeamId20DSL();

    // 21번
    List<?> getOnByPositionAndTeamId21DSL();

    // 22번
    List<?> getHeightAndTeamIdDSL();
}
