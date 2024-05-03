package com.james.api.team.repository;
import com.james.api.team.model.QTeam;
import com.james.api.team.model.TeamDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<TeamDto> getAllTeams() {
        return jpaQueryFactory.select(
                        QTeam.team.id,
                        QTeam.team.teamId,
                        QTeam.team.regionName,
                        QTeam.team.teamName,
                        QTeam.team.eTeamName,
                        QTeam.team.origYyyy,
                        QTeam.team.zipCode1,
                        QTeam.team.zipCode2,
                        QTeam.team.address,
                        QTeam.team.ddd,
                        QTeam.team.tel,
                        QTeam.team.fax,
                        QTeam.team.homepage,
                        QTeam.team.owner,
                        QTeam.team.regDate,
                        QTeam.team.modDate)
                .from(QTeam.team)
                .fetch()
                .stream()
                .map(tuple -> TeamDto.builder()
                        .id(tuple.get(QTeam.team.id))
                        .teamId(tuple.get(QTeam.team.teamId))
                        .regionName(tuple.get(QTeam.team.regionName))
                        .teamName(tuple.get(QTeam.team.teamName))
                        .eTeamName(tuple.get(QTeam.team.eTeamName))
                        .origYyyy(tuple.get(QTeam.team.origYyyy))
                        .zipCode1(tuple.get(QTeam.team.zipCode1))
                        .zipCode2(tuple.get(QTeam.team.zipCode2))
                        .address(tuple.get(QTeam.team.address))
                        .ddd(tuple.get(QTeam.team.ddd))
                        .tel(tuple.get(QTeam.team.tel))
                        .fax(tuple.get(QTeam.team.fax))
                        .homepage(tuple.get(QTeam.team.homepage))
                        .owner(tuple.get(QTeam.team.owner))
                        .regDate(tuple.get(QTeam.team.regDate))
                        .modDate(tuple.get(QTeam.team.modDate))
                        .build()).toList();
    }
}