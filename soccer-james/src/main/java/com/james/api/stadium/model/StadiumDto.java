package com.james.api.stadium.model;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@NoArgsConstructor
@Data
@Log4j2
public class StadiumDto {
    private Long id;
    private String stadiumId;
    private String stadiumName;
    private String hometeamId;
    private Integer seatCount;
    private String address;
    private String ddd;
    private String tel;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private String teamName;
    private String awayteamName;
    private String playerName;
    private String position;
    private String scheDate;


    @QueryProjection
    public StadiumDto(Long id, String stadiumId, String stadiumName, String hometeamId, Integer seatCount, String address, String ddd, String tel, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.hometeamId = hometeamId;
        this.seatCount = seatCount;
        this.address = address;
        this.ddd = ddd;
        this.tel = tel;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    @QueryProjection
    public StadiumDto(String stadiumName,String teamName){
        this.stadiumName=stadiumName;
        this.teamName=teamName;
    }

    @QueryProjection
    public StadiumDto(String stadiumName,String teamName,String awayteamName){
        this.stadiumName=stadiumName;
        this.teamName=teamName;
        this.awayteamName=awayteamName;
    }
    @QueryProjection
    public StadiumDto(String playerName,String position,String stadiumName,String teamName,String scheDate){
        this.stadiumName=stadiumName;
        this.position=position;
        this.teamName=teamName;
        this.stadiumName=stadiumName;
        this.scheDate=scheDate;
    }
}