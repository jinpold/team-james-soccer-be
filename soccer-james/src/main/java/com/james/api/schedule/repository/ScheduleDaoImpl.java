package com.james.api.schedule.repository;

import com.james.api.schedule.model.QSchedule;
import com.james.api.schedule.model.QScheduleDto;
import com.james.api.schedule.model.ScheduleDto;
import com.james.api.stadium.model.QStadium;
import com.james.api.stadium.model.QStadiumDto;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSchedule schedule=QSchedule.schedule;
    private final QStadium stadium=QStadium.stadium;

    @Override
    public List<ScheduleDto> getInfoInScheduleByDate(@Param("startDate") String startDate, @Param("endDate") String endDate) {
                return jpaQueryFactory.select(new QScheduleDto(
                        JPAExpressions.select(stadium.stadiumName.as("stadiumName")).from(stadium).where(stadium.id.eq(schedule.stadium.id)),
                        schedule.scheDate.as("scheDate")
                        )
                ).distinct()
                .from(schedule)
                .where(schedule.scheDate.between(startDate,endDate))
                .fetch();
    }
}


