package com.james.api.player.service;
import com.james.api.player.model.Player;
import com.james.api.player.model.PlayerDto;
import com.james.api.player.repository.PlayerRepository;
import com.james.api.team.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;



    @Override
    public List<?> getDistinctByPosition() {
        return null;
    }

    @Override
    public List<?> getDistinctByPositionNotNull() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamId() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamId2() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamIdAndHeight() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamIdAndHeight2() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndHeightAndTeamId() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamId7() {
        return null;
    }

    @Override
    public List<?> getOnByHeightAndWeight() {
        return null;
    }

    @Override
    public List<?> getOnByPositionAndTeamId10() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getOnCountAll() {
        return null;
    }

    @Override
    public List<?> getByOnPositionAndTeamId20() {
        return null;
    }
    @Override
    public List<Map<String, Object>> getOnByPositionAndTeamId21() {
        return null;
    }



}