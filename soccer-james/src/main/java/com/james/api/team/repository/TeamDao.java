package com.james.api.team.repository;

import com.james.api.stadium.model.Stadium;
import com.james.api.team.model.Team;

public interface TeamDao {
    Team findById(Long id);

    void insert(Team team);
    void update(Team team);
}