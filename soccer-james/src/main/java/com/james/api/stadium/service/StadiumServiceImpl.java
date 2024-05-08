package com.james.api.stadium.service;

import com.james.api.schedule.repository.ScheduleDaoImpl;
import com.james.api.stadium.model.StadiumDto;
import com.james.api.stadium.repository.StadiumDao;
import com.james.api.stadium.repository.StadiumDaoImpl;
import com.james.api.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {

    private final StadiumDaoImpl stadiumDao;
    @Override
    public Long countAll() {
        return stadiumDao.countAll();
    }
}