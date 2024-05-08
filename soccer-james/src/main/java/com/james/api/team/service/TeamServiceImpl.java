package com.james.api.team.service;

import com.james.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{
    private final TeamRepository repository;
    @Override
    public Long countAllTeams() {
        return repository.countAllTeams();
    }
}