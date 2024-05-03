package com.james.api.stadium.repository;

import com.james.api.schedule.model.Schedule;
import com.james.api.stadium.model.Stadium;
import com.james.api.stadium.model.StadiumDto;
import com.james.api.team.model.TeamDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface StadiumDao {

    List<StadiumDto> getAllStadiums();
}