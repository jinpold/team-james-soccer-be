package com.james.api.schedule.repository;

import com.james.api.schedule.model.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {

    @PersistenceContext
    private final EntityManager em;
    @Override
    public Schedule findById(Long id) {
        return (Schedule) em.createQuery("FROM schedules sc WHERE sc.id = :id")
                .setParameter("id", id)
                .getSingleResult();

        //or return em.find(Schedule.class, id);
    }

    @Override
    public void insert(Schedule schedule) {
        em.persist(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        em.persist(schedule);
    }
}