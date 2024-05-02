package com.james.api.player.web;
import com.james.api.player.repository.PlayerRepository;
import com.james.api.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class PlayerRouter {

    private final PlayerRepository playerRepository;
    private PlayerService playerService;

    @SuppressWarnings("unchecked")
    public  List<Map<String,Object>> execute(String q, String position, String teamId1,
                                             String teamId2, String regionName, String height1, String height2,
                                             String playerName, String teamName1, String teamName2, Integer limit) {
        return switch (q) {
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


            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }
}
//.stream().filter(i->Integer.parseInt((String)i.get("height")) >=170).toList();



