package com.james.api.team.repository;
import com.james.api.team.model.TeamDto;

import java.util.List;

public interface TeamDao {

    List<TeamDto> getAllTeams();

}