package com.james.api.stadium.repository;

import com.james.api.schedule.model.Schedule;
import com.james.api.stadium.model.Stadium;
import lombok.RequiredArgsConstructor;

public interface StadiumDao {
    Stadium findById(Long id);

    void insert(Stadium schedule);
    void update(Stadium schedule);
}