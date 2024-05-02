package com.james.api.schedule.repository;

import com.james.api.schedule.model.Schedule;
import com.james.api.schedule.model.ScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("hiding")
@Repository
public interface ScheduleJpqlRepository extends JpaRepository<Schedule,Long> {
    // problem 23
    //-- 2012년 5월 한달간 경기가 있는 경기장  조회
    @Query("select distinct new map((select s.stadiumName from stadiums s where s.id = sc.stadium.id) as stadium) " +
            "from schedules sc " +
            "where sc.scheDate between :startDate and :endDate")
    List<Map<String, Object>> getInfoInScheduleByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

}