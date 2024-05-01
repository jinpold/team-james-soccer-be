package com.james.api.team.service;

import com.james.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;

    @Override
    public List<?> getTeamsOrderByTeamName() {
        return null;
    }

    @Override
    public List<?> getTeamsByteamName() {
        return null;
    }

    @Override
    public List<?> getNotSelectedPostion() {
        return null;
    }

    @Override
    public List<?> getHeightAvgByTeam() {
        return null;
    }
}
