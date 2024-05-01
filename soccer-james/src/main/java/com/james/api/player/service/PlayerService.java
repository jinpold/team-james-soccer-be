package com.james.api.player.service;

import com.james.api.player.model.Player;
import com.james.api.player.model.PlayerDto;
import com.james.api.schedule.model.ScheduleDto;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface PlayerService {

    List<?> getDistinctByPosition();

    List<?> getDistinctByPositionNotNull();

    List<?> getOnByPositionAndTeamId();

    List<?> getOnByPositionAndTeamId2();

    List<?> getOnByPositionAndTeamIdAndHeight();

    List<?> getOnByPositionAndTeamIdAndHeight2();

    List<?> getOnByPositionAndHeightAndTeamId();

    List<?> getOnByPositionAndTeamId7();

    List<?> getOnByHeightAndWeight();

    List<?> getOnByPositionAndTeamId10();

    List<?> getOnCountAll();

    List<?> getByOnPositionAndTeamId20();

    List<?> getOnByPositionAndTeamId21();

//    List<?> getPlayersTeamAverage();

}
