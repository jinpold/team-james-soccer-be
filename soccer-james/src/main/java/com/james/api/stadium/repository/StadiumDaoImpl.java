package com.james.api.stadium.repository;

import com.james.api.stadium.model.Stadium;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StadiumDaoImpl implements StadiumDao{

    @PersistenceContext
    private final EntityManager em;
    @Override
    public Stadium findById(Long id) {
        return em.find(Stadium.class,id);
    }

    @Override
    public void insert(Stadium stadium) {
        em.persist(stadium);
    }

    @Override
    public void update(Stadium stadium) {
        em.persist(stadium);
    }
}