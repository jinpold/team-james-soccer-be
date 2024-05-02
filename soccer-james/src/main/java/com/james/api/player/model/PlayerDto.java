package com.james.api.player.model;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
}