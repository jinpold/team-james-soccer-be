package com.james.api.team.web;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.james.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@Component
public class TeamRouter {

    private final TeamRepository repository;

    public List<?> execute(String q, int pageNumber, int pageSize) {

        return switch (q){
            case "1"-> repository.getAllByTeamName();
            case "12"-> repository.getPlayerByTeamId();
            case "13"-> repository.getTeamByNoPosition();
            case "14" -> repository.getTeamByDate();
            case "19" -> repository.getTeamByAvgHeight();
            case "21" -> repository.getTeamByHeight();
            default -> null;
        };
    }

}