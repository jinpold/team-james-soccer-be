package com.james.api.team.web;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.james.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@Component
public class TeamRouter {

    private final TeamRepository repository;

//    public List<Map<String, Object>> execute(String type) {
//
//        return switch (type){
//            case "1"-> repository.getAllByTeamName();
//            case "2"-> repository.getPlayerOrderBy();
//            case "3"-> repository.getPlayerByTeamId();
//            case "4" -> repository.getTeamByNoPosition();
//            case "5" -> repository.getTeamByDate();
//            case "6" -> repository.getTeamByHeight();
//            default -> null;
//        };
//    }
}

