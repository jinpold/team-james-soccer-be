package com.james.api.player.repository;

import com.james.api.player.model.PlayerDto;
import com.james.api.player.model.QPlayer;
import com.james.api.player.model.QPlayerDto;
import com.james.api.schedule.model.QSchedule;
import com.james.api.stadium.model.QStadium;
import com.james.api.team.model.QTeam;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlayerDaoImpl implements PlayerDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPlayer player = QPlayer.player;
    private final QTeam team = QTeam.team;
    private final QStadium stadium = QStadium.stadium;
    private final QSchedule schedule = QSchedule.schedule;

    @Override
    public List<PlayerDto> getAllPlayersDSL() {
        return jpaQueryFactory.select(
                new QPlayerDto(
                        player.id,
                        player.playerId,
                        player.name,
                        player.team.teamId,
                        player.playerName,
                        player.ePlayerName,
                        player.nickname,
                        player.joinYyyy,
                        player.position,
                        player.backNo,
                        player.nation,
                        player.birthDate,
                        player.solar,
                        player.height,
                        player.weight,
                        player.regDate,
                        player.modDate))
                .from(player)
                .fetch();
    }
    @Override
    public List<PlayerDto> getDistinctByPositionDSL() {
        return jpaQueryFactory.select(
                new QPlayerDto(player.position))
                .from(player)
                .distinct()
                .offset(0)
                .limit(5)
                .fetch();
    }
    @Override
    public List<String> getDistinctByPositionNotNullDSL() {
        return jpaQueryFactory
                .select(player.position.coalesce("신입"))
                .distinct()
                .from(player)
                .fetch();
    }

    @Override
    public List<Tuple> getOnByPositionAndTeamIdDSL(String position, String teamId1) {

        return jpaQueryFactory
                .select(player.team.teamId, player.position)
                .from(player)
                .leftJoin(player.team, team)
                .where(player.position.eq(position),
                        teamId1 != null ? player.team.teamId.eq(teamId1) : player.team.teamId.isNull())
                .fetch();
    }

    @Override
    public List<PlayerDto> getOnByPositionAndTeamId2DSL(String regionName) {

        QTeam subTeam = new QTeam("subTeam"); // 서브쿼리에 사용할 별칭

        return jpaQueryFactory
                .select(Projections.bean(PlayerDto.class, player.position, team.teamId))
                .from(player)
                .join(player.team, team)
                .where(
                        player.position.eq("GK"),
                        team.teamId.eq(
                                jpaQueryFactory
                                        .select(subTeam.teamId)
                                        .from(subTeam)
                                        .where(subTeam.regionName.eq(regionName))
                        )
                )
                .fetch();
    }

    @Override
    public List<PlayerDto> getOnByPositionAndTeamIdAndHeightDSL(String regionName1) {

        return jpaQueryFactory
                .select(Projections.constructor(PlayerDto.class,
                        player.team.teamId,
                        player.height,
                        player.playerName))
                .from(player)
                .where(player.height.isNotNull(),
                        player.playerName.startsWith("고"),
                        player.team.teamId.eq(
                                jpaQueryFactory.select(team.teamId)
                                        .from(team)
                                        .where(team.regionName.eq(regionName1))
                                        .fetchOne()
                        ))
                .fetch();
    }

    @Override
    public List<PlayerDto> getOnByPositionAndTeamIdAndHeight2DSL(String playerName, String regionName, String height) {

        return jpaQueryFactory
                .select(Projections.constructor(PlayerDto.class,
                        player.team.teamId,
                        player.height,
                        player.playerName))
                .from(player)
                .join(player.team, team)
                .where(player.height.eq(String.valueOf(height)),
                        player.playerName.like(playerName.concat("%")),
                        team.regionName.eq(regionName))
                .fetch();
    }

    @Override
    public List<PlayerDto> getOnByPositionAndHeightAndTeamIdDSL(String position, String height1, String height2, String teamName1, String teamName2) {

        return jpaQueryFactory
                .select(Projections.constructor(PlayerDto.class,
                        player.playerName,
                        player.position,
                        player.team.teamId))
                .from(player)
                .join(player.team, team)
                .where(player.position.eq(position),
                        player.height.between(height1, height2),
                        team.teamName.in(teamName1, teamName2))
                .fetch();
    }

    @Override
    public List<Map<String, Object>> getPositionAndRegionDSL() {
        List<Tuple> ls = jpaQueryFactory
                .select(player.position, player.team.teamId)
                .from(player)
                .leftJoin(player.team, team)
                .where(player.position.eq("GK"), player.team.regionName.eq(team.regionName))
                .fetch();

        return ls.stream().map(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("position", tuple.get(player.position));
            map.put("teamId", tuple.get(player.team.teamId));
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<?> getOnByHeightAndWeightDSL(String regionName) {
         return jpaQueryFactory
                .select(player.playerName,
                        Expressions.cases()
                                .when(player.height.isNotNull())
                                .then(player.height)
                                .otherwise("0")
                                .as("height"),
                        Expressions.cases()
                                .when(player.weight.isNotNull())
                                .then(player.weight)
                                .otherwise("0")
                                .as("weight"))
                .from(player)
                .leftJoin(player.team, team)
                .where(team.regionName.eq(regionName))
                .fetch();
    }

    @Override
    public List<PlayerDto> getPlayerInfoByRegionDSL() {
        return null;
    }

    @Override
    public List<PlayerDto> getOnByPositionAndTeamId10DSL() {
        return null;
    }

    @Override
    public List<PlayerDto> getOnCountAllDSL() {
        return jpaQueryFactory.select(
                        new QPlayerDto(
                                player.name,
                                player.team.teamId,
                                player.playerName,
                                player.ePlayerName,
                                player.nickname,
                                player.position,
                                player.backNo,
                                player.solar))
                .from(player)
                .fetch();
    }

    @Override
    public List<PlayerDto> getByOnPositionAndTeamId20DSL() {
        return null;
    }

    @Override
    public List<PlayerDto> getOnByPositionAndTeamId21DSL() {
        return null;
    }

    @Override
    public List<PlayerDto> getHeightAndTeamIdDSL() {
       return null;
    }
}
