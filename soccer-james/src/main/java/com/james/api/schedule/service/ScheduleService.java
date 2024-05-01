package com.james.api.schedule.service;
import com.james.api.schedule.model.ScheduleDto;
import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getInfoInScheduleByDate(String date);

    List<ScheduleDto> getInfoInScheduleByScoreGap(String score);
}