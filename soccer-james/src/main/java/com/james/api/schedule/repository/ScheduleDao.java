package com.james.api.schedule.repository;
import com.james.api.schedule.model.ScheduleDto;
import java.util.List;
public interface ScheduleDao {

    List<ScheduleDto> getAllSchedules();

}