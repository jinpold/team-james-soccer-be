package com.james.api.stadium.web;

import com.james.api.stadium.model.StadiumDto;
import com.james.api.stadium.repository.StadiumDaoImpl;
import com.james.api.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

//Service layer를 거치치 않는 Search를 위한 Component

@Slf4j
@Component
@RequiredArgsConstructor
public class StadiumRouter {
    private final StadiumRepository repository;
    private final StadiumDaoImpl stadiumDao;

    public List<StadiumDto> execute(String q, String regionName, String date, String teamName, String position, String score, int pageNumber, int pageSize){
        return switch (q){
            case "11"-> stadiumDao.getStadiumNameWithTeam(regionName);
            case "14"-> stadiumDao.getStadiumAndTeamAndSchedule(date);
            case "15"-> stadiumDao.getPohangSteelersGk(date,teamName,position);
//            case "16"-> repository.getHomeTeamWin(score);
//            case "17"-> repository.getNoHomeTeam();
            default -> null;
        };
    }



}