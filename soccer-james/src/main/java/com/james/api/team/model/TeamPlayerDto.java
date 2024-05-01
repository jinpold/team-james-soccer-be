package com.james.api.team.model;

import org.springframework.stereotype.Component;

import com.james.api.stadium.model.Stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPlayerDto {
    // Team
    private String teamId;
    private String regionName;
    private String teamName;
    private String eTeamName;
    private String origYyyy;

    private String zipCode1;
    private String zipCode2;
    private String address;
    private String ddd;
    private String tel;

    private String fax;
    private String homepage;
    private String owner;
    private String stadiumId;

    // Player
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
}