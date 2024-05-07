package com.james.api.team.model;
import com.querydsl.core.annotations.QueryProjection;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
public class TeamDto {
    private Long id;
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
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @QueryProjection
    public TeamDto(Long id, String teamId, String regionName, String teamName, String eTeamName, String origYyyy, String zipCode1, String zipCode2, String address, String ddd, String tel, String fax, String homepage, String owner, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.teamId = teamId;
        this.regionName = regionName;
        this.teamName = teamName;
        this.eTeamName = eTeamName;
        this.origYyyy = origYyyy;
        this.zipCode1 = zipCode1;
        this.zipCode2 = zipCode2;
        this.address = address;
        this.ddd = ddd;
        this.tel = tel;
        this.fax = fax;
        this.homepage = homepage;
        this.owner = owner;
        this.regDate = regDate;
        this.modDate = modDate;
    }

}