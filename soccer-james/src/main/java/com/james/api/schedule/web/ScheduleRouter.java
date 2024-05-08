package com.james.api.schedule.web;

import com.james.api.schedule.model.ScheduleDto;
import com.james.api.schedule.repository.ScheduleDaoImpl;
import com.james.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduleRouter {

    private final ScheduleRepository repository;
    private final ScheduleDaoImpl scheduleDao;
    @SuppressWarnings("unchecked")
    public List<ScheduleDto> execute(String q, String startDate, String endDate) {
        log.info("Controller Search Schedule {}, {}, {}",q,startDate,endDate);
        return switch (q){
            case "23"->scheduleDao.getInfoInScheduleByDate(startDate,endDate);
            default -> null;
        };

    }
}