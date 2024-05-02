package com.james.api.schedule.repository;
import com.james.api.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//interface는 속성을 가질 수 x
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>,ScheduleJpqlRepository {

}