package com.james.api.player.model;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@ToString
@NoArgsConstructor
@Builder
@Log4j2
public class PlayerDto {
    private Long id;
    private String playerId;
    private String name;
    private String teamId;
    private String playerName;
    private String ePlayerName;
    private String nickname;
    private String joinYyyy ;
    private String position ;
    private String backNo ;
    private String nation ;
    private String birthDate ;
    private String solar ;
    private String height ;
    private String weight ;
    private LocalDateTime regDate;
    private LocalDateTime modDate;


    @QueryProjection
    public PlayerDto(Long id, String playerId, String name, String teamId, String playerName, String ePlayerName, String nickname, String joinYyyy, String position, String backNo, String nation, String birthDate, String solar, String height, String weight, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.teamId = teamId;
        this.playerName = playerName;
        this.ePlayerName = ePlayerName;
        this.nickname = nickname;
        this.joinYyyy = joinYyyy;
        this.position = position;
        this.backNo = backNo;
        this.nation = nation;
        this.birthDate = birthDate;
        this.solar = solar;
        this.height = height;
        this.weight = weight;
        this.regDate = regDate;
        this.modDate = modDate;
    }
    @QueryProjection
    public PlayerDto(String position) {
        this.position = position;
    }
    @QueryProjection
    public PlayerDto(String name, String teamId, String playerName, String ePlayerName, String nickname, String position, String backNo, String solar) {
        this.name = name;
        this.teamId = teamId;
        this.playerName = playerName;
        this.ePlayerName = ePlayerName;
        this.nickname = nickname;
        this.position = position;
        this.backNo = backNo;
        this.solar = solar;
    }
}