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
    public  List<Map<String,Object>> execute(String q) {
        return switch (q) {
            case "2" -> playerRepository.getDistinctByPosition();
            case "3" -> playerRepository.getDistinctByPositionNotNull();
            case "4" -> playerRepository.getOnByPositionAndTeamId();
            case "4-1" -> playerRepository.getOnByPositionAndTeamId2();
            case "5" -> playerRepository.getOnByPositionAndTeamIdAndHeight();
            case "5-1" -> playerRepository.getOnByPositionAndTeamIdAndHeight2();
            case "6" -> playerRepository.getOnByPositionAndHeightAndTeamId();
            case "8" -> playerRepository.getOnByPositionAndTeamId7();
            case "9" -> playerRepository.getOnByHeightAndWeight();
            case "10" -> playerRepository.getOnByPositionAndTeamId10();
            case "18" -> playerRepository.getOnCountAll();
            case "20" -> playerRepository.getByOnPositionAndTeamId20();
            case "21" -> playerRepository.getOnByPositionAndTeamId21();
//            case "22" -> playerRepository.getPlayersTeamAverage();


            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }
}



