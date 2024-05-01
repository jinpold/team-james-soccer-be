package com.james.api.team.web;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.james.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Component
public class TeamRouter {

    private final TeamRepository teamRepository;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> execute(String q){
        return switch (q) {
            case "1" -> teamRepository.getTeamsOrderByTeamName();
            case "12" -> teamRepository.getTeamsByteamName();
            case "13" -> teamRepository.getNotSelectedPostion();
            case "19" -> teamRepository.getHeightAvgByTeam();
            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }
}




