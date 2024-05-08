package com.james.api.schedule.repository;
import com.james.api.schedule.model.ScheduleDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ScheduleDao {

    List<ScheduleDto> getInfoInScheduleByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

}