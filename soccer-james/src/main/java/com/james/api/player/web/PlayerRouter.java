package com.james.api.player.web;

import com.james.api.player.repository.PlayerRepository;
import com.james.api.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PlayerRouter {

    private final PlayerRepository playerRepository;
    private PlayerService playerService;

    @SuppressWarnings("unchecked")
    public  List<?> execute(String q, String position, String teamId1,
                                             String teamId2, String regionName, String height1, String height2,
                                             String playerName, String teamName1, String teamName2, Integer limit) {
        return switch (q) {
            //JPQL
            case "2" -> playerRepository.getDistinctByPosition();
            case "3" -> playerRepository.getDistinctByPositionNotNull();
            case "4" -> playerRepository.getOnByPositionAndTeamId(position, teamId1);
            case "4-1" -> playerRepository.getOnByPositionAndTeamId2(regionName);
            case "5" -> playerRepository.getOnByPositionAndTeamIdAndHeight(regionName);
            case "5-1" -> playerRepository.getOnByPositionAndTeamIdAndHeight2(height1, playerName, regionName);
            case "6" -> playerRepository.getOnByPositionAndHeightAndTeamId(teamName1, teamName2,position, height1, height2);
            case "7" -> playerRepository.getPositionAndeRegion(regionName);
            case "8" -> playerRepository.getOnByHeightAndWeight(regionName);
            case "9" -> playerRepository.getPlayerInfoByRegion();
            case "10" -> playerRepository.getOnByPositionAndTeamId10(position, teamId1, teamId2);
            case "18" -> playerRepository.getOnCountAll(limit);
            case "20" -> playerRepository.getByOnPositionAndTeamId20(position);
            case "21" -> playerRepository.getOnByPositionAndTeamId21(teamId1);
            case "22" -> playerRepository.getHeightAndTeamId();

            //QueryDSL
            case "player-No2" -> playerRepository.getDistinctByPositionDSL();
            case "player-No3" -> playerRepository.getDistinctByPositionNotNullDSL();
            case "player-No4" -> playerRepository.getOnByPositionAndTeamIdDSL(position, teamName1);
            case "player-No4-1" -> playerRepository.getOnByPositionAndTeamId2DSL(regionName);
            case "player-No5" -> playerRepository.getOnByPositionAndTeamIdAndHeightDSL(regionName);
            case "player-No5-1" -> playerRepository.getOnByPositionAndTeamIdAndHeight2DSL(playerName, regionName, height1);
            case "player-No6" -> playerRepository.getOnByPositionAndHeightAndTeamIdDSL(teamName1, teamName2,position, height1, height2);
            case "player-No7" -> playerRepository.getPositionAndRegionDSL();
            case "player-No8" -> playerRepository.getOnByHeightAndWeightDSL(regionName);
            case "player-No9" -> playerRepository.getPlayerInfoByRegionDSL();
            case "player-No10" -> playerRepository.getOnByPositionAndTeamId10DSL();
            case "player-No18" -> playerRepository.getOnCountAllDSL();
            case "player-No20" -> playerRepository.getByOnPositionAndTeamId20DSL();
            case "player-No21" -> playerRepository.getOnByPositionAndTeamId21DSL();
            case "player-No22" -> playerRepository.getHeightAndTeamIdDSL();
            
            
            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }
}
//.stream().filter(i->Integer.parseInt((String)i.get("height")) >=170).toList();



