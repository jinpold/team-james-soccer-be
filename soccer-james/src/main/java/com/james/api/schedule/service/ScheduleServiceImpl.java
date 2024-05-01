package com.james.api.schedule.service;

import com.james.api.schedule.model.ScheduleDto;
import com.james.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository repository;

    @Override
    public List<ScheduleDto> getInfoInScheduleByDate(String date) {
        return repository.getInfoInScheduleByDate(date);
    }

    @Override
    public List<ScheduleDto> getInfoInScheduleByScoreGap(String score) {
        return repository.getInfoInScheduleByScoreGap(score);
    }
}