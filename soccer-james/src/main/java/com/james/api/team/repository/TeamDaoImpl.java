package com.james.api.team.repository;

import com.james.api.player.model.QPlayer;
import com.james.api.schedule.model.QSchedule;
import com.james.api.stadium.model.QStadium;
import com.james.api.team.model.QTeam;
import com.james.api.team.model.QTeamDto;
import com.james.api.team.model.TeamDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final QTeam team = QTeam.team;
    private final QPlayer player = QPlayer.player;
    private final QStadium stadium = QStadium.stadium;
    private final QSchedule schedule = QSchedule.schedule;

    @Override
    public List<TeamDto> getAllTeams() {
        return jpaQueryFactory.select(
                        new QTeamDto(
                                team.id,
                                team.teamId,
                                team.regionName,
                                team.teamName,
                                team.eTeamName,
                                team.origYyyy,
                                team.zipCode1,
                                team.zipCode2,
                                team.address,
                                team.ddd,
                                team.tel,
                                team.fax,
                                team.homepage,
                                team.owner,
                                team.regDate,
                                team.modDate))
                .from(team)
                .fetch();
    }

    @Override
    public List<String> getAllByTeamName() {
        List<String> ls = jpaQueryFactory
                .select(team.teamName)
                .from(team)
                .orderBy(team.teamName.asc())
                .offset(0)
                .limit(15)
                .fetch();
        return ls;
    }

    @Override
    public List<?> getPlayerByTeamId() {
        return jpaQueryFactory
                .select(team.teamName.as("teamName"),
                        player.playerName.as("playerName"),
                        player.height.as("height"))
                .from(team)
                .join(player)
                .on(team.teamId.eq(player.team.teamId))
                .where(team.teamId.in("K02", "k10"))
                .orderBy(team.teamName.asc(), player.playerName.asc())
                .fetch();
    }

    @Override
    public List<Map<String, Object>> getTeamByNoPosition() {
        return null;
    }

    @Override
    public List<Tuple> getTeamByDate() {

            return jpaQueryFactory
                    .select(
                            team.teamName.as("hometeamName"),
                            stadium.stadiumName.as("stadiumName"),
                            jpaQueryFactory
                                    .select(team.teamName)
                                    .from(team)
                                    .where(team.teamId.eq(schedule.awayteamId))
                    )
                    .from(schedule)
                    .join(schedule.stadium, stadium)
                    .where(schedule.scheDate.eq("20120317"))
                    .fetch();
        }

    @Override
    public List<Map<?, ?>> getTeamByAvgHeight() {
        List<Tuple> ls = jpaQueryFactory
                .select(
                        team.teamId.as("팀ID"),
                        team.teamName.as("팀명"),
                        player.height.castToNum(Double.class).avg().round().as("평균")
                )
                .from(player)
                .join(player.team, team)
                .where(player.height.isNotNull())
                .groupBy(team.teamId, team.teamName)
                .fetch();

        return ls.stream().map(tuple -> {
            return Map.of(
                   "팀ID", tuple.get(team.teamId.as("팀ID")),
                   "팀명", tuple.get(team.teamName.as("팀명")),
                   "평균", tuple.get(player.height.castToNum(Double.class).avg().round().as("평균"))
           );
        }).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamByHeight() {
        return jpaQueryFactory
                .select(Projections.fields(TeamDto.class,
                        team.teamName.as("teamName"),
                        player.playerName.as("playerName"),
                        player.backNo.as("backNo")))
                .from(player)
                .join(player.team, team)
                .orderBy(player.height.desc())
                .limit(5)
                .fetch();
    }


}