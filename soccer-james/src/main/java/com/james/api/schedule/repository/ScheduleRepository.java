package com.james.api.schedule.repository;
import com.james.api.schedule.model.Schedule;
import com.james.api.schedule.model.ScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query("select new com.james.api.schedule.model.ScheduleDto(t.teamName, sta.stadiumName, " +
            "(select t2.teamName from teams t2 where t2.id = CAST(sch.awayteamId AS long))) " +
            "from schedules sch " +
            "join teams t on sch.hometeamId = CAST(t.id AS string) " +
            "join stadiums sta on sch.stadiumId = CAST(sta.id AS string) " +
            "where sch.scheDate = :date")
    List<ScheduleDto> getInfoInScheduleByDate(@Param("date") String scheDate);

    @Query("select new com.james.api.schedule.model.ScheduleDto(s.hometeamId, s.awayteamId," +
            " (select s2.stadiumName from stadiums s2 where s2.id = CAST(s.stadiumId AS long))) " +
            "from schedules s " +
            "where s.homeScore - s.awayScore >= cast(:score as int)"
    )
    List<ScheduleDto> getInfoInScheduleByScoreGap(@Param("score") String score);
}