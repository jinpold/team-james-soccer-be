package com.james.api.player.service;
import java.util.List;

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



}
