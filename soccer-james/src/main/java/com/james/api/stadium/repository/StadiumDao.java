package com.james.api.stadium.repository;

import com.james.api.schedule.model.Schedule;
import com.james.api.stadium.model.Stadium;
import com.james.api.stadium.model.StadiumDto;
import com.james.api.team.model.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface StadiumDao {

    List<StadiumDto> getStadiumNameWithTeam(@Param("regionName") String regionName);
    List<StadiumDto> getStadiumAndTeamAndSchedule(@Param("date") String date);
    List<StadiumDto> getPohangSteelersGk(@Param("date") String date, @Param("teamName") String teamName, @Param("position") String position);
    List<StadiumDto> getHomeTeamWin(@Param("score") String score);
    List<StadiumDto> getNoHomeTeam();
    Long countAll();
}