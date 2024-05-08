package com.james.api.schedule.model;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
@Log4j2
public class ScheduleDto {
    private Long id;
    private String scheDate;
    private String gubun;
    private String hometeamId;
    private String awayteamId;
    private Integer homeScore;
    private Integer awayScore;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private String stadiumName;

    @QueryProjection
    public ScheduleDto(String stadiumName,String scheDate) {
        this.stadiumName = stadiumName;
        this.scheDate=scheDate;
    }
}