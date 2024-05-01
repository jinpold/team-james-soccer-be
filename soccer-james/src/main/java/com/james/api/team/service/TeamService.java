package com.james.api.team.service;

import java.util.List;

public interface TeamService  {

    List<?> getTeamsOrderByTeamName();
    List<?> getTeamsByteamName();
    List<?> getNotSelectedPostion();
    List<?> getHeightAvgByTeam();

}