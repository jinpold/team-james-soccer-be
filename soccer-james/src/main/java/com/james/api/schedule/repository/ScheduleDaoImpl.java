package com.james.api.schedule.repository;

import com.james.api.schedule.model.QSchedule;
import com.james.api.schedule.model.ScheduleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ScheduleDto> getAllSchedules() {
        return jpaQueryFactory.select(
                        QSchedule.schedule.id,
                        QSchedule.schedule.scheDate,
                        QSchedule.schedule.gubun,
                        QSchedule.schedule.hometeamId,
                        QSchedule.schedule.awayteamId,
                        QSchedule.schedule.homeScore,
                        QSchedule.schedule.awayScore,
                        QSchedule.schedule.regDate,
                        QSchedule.schedule.modDate)
                .from(QSchedule.schedule)
                .fetch()
                .stream()
                .map(tuple -> ScheduleDto.builder()
                        .id(tuple.get(QSchedule.schedule.id))
                        .scheDate(tuple.get(QSchedule.schedule.scheDate))
                        .gubun(tuple.get(QSchedule.schedule.gubun))
                        .hometeamId(tuple.get(QSchedule.schedule.hometeamId))
                        .awayteamId(tuple.get(QSchedule.schedule.awayteamId))
                        .homeScore(tuple.get(QSchedule.schedule.homeScore))
                        .regDate(tuple.get(QSchedule.schedule.regDate))
                        .modDate(tuple.get(QSchedule.schedule.modDate))
                        .build()).toList();
    }
}


