package com.james.api.team.repository;

import com.james.api.stadium.model.Stadium;
import com.james.api.team.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Team findById(Long id) {
        return em.find(Team.class,id);
    }

    @Override
    public void insert(Team team) {
        em.persist(team);
    }

    @Override
    public void update(Team team) {
        em.persist(team);
    }
}