package com.james.api.schedule.web;

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

    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> execute(String q, String startDate, String endDate) {
        log.info("Controller Search Schedule {}, {}, {}",q,startDate,endDate);
        return switch (q){
            case "23"->repository.getInfoInScheduleByDate(startDate,endDate);
            default -> null;
        };

    }
}