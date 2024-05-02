package com.james.api.schedule.repository;
import com.james.api.schedule.model.Schedule;

public interface ScheduleDao {
    Schedule findById(Long id);
    void insert(Schedule schedule);
    void update(Schedule schedule);
}