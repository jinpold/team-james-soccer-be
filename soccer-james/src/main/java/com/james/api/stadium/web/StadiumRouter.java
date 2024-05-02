package com.james.api.stadium.web;

import com.james.api.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//Service layer를 거치치 않는 Search를 위한 Component

@Slf4j
@Component
@RequiredArgsConstructor
public class StadiumRouter {
    private final StadiumRepository repository;

    public List<Map<String,Object>> execute(String q, String regionName, String date, String teamName, String position, String score){
        return switch (q){
            case "11"-> repository.getStadiumNameWithTeam(regionName);
            case "14"-> repository.getStadiumAndTeamAndSchedule(date);
            case "15"-> repository.getPohangSteelersGk(date,teamName,position);
            case "16"-> repository.getHomeTeamWin(score);
            case "17"-> repository.getNoHomeTeam();
            default -> null;
        };
    }



}