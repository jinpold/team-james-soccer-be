package com.james.api.player.repository;
import com.james.api.player.model.PlayerDto;
import com.james.api.player.model.QPlayer;
import com.james.api.player.model.QPlayerDto;
import com.james.api.schedule.model.QSchedule;
import com.james.api.stadium.model.QStadium;
import com.james.api.team.model.QTeam;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPlayer player = QPlayer.player;
    private final QTeam team = QTeam.team;
    private final QStadium stadium = QStadium.stadium;
    private final QSchedule schedule = QSchedule.schedule;

    @Override
    public Long countAllPlayers() {
       return jpaQueryFactory.select(player.count())
                .from(player)
                .fetchFirst();
    }

    // 0
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

    //2
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

    //3
    @Override
    public List<String> getDistinctByPositionNotNullDSL() {
        return jpaQueryFactory
                .select(player.position.coalesce("신입").as("포지션"))
                .distinct()
                .from(player)
                .fetch();
    }

    //4 X
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

    // 4-1 O
    @Override
    public List<String> getOnByPositionAndTeamId2DSL() {

        return jpaQueryFactory
                .select(player.team.teamId.stringValue().concat(", ").concat(player.position))
                .from(player)
                .where(player.position.eq("GK").and(player.team.teamId.eq("k02")))
                .fetch();
    }

    // 5
    @Override
    public List<PlayerDto> getOnByPositionAndTeamIdAndHeightDSL(String regionName1) {

        return jpaQueryFactory.select(new QPlayerDto(player.playerName))
                .from(player)
                .where(player.playerName.like("고%"),
                        player.height.goe("170"),
                        player.team.teamId.eq("K02"))
                .fetch();
    }
    // 5-1

    @Override
    public List<PlayerDto> getOnByPositionAndTeamIdAndHeight2DSL(String playerName, String regionName, String height) {

        return jpaQueryFactory.select(new QPlayerDto(player.playerName))
                .from(player)
                .where(player.playerName.like("고%"),
                        player.height.goe("170"),
                        player.team.regionName.eq("수원"))
                .fetch();
    }

    // 6

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

    // 7 O

    @Override
    public List<PlayerDto> getPositionAndRegionDSL() {
        return jpaQueryFactory.select(new QPlayerDto(player.playerName))
                .from(player)
                .where(player.position.eq("GK")
                        .and(player.team.regionName.eq("수원")))
                .fetch();
    }
//        List<Tuple> ls = jpaQueryFactory
//                .select(player.position, player.team.teamId)
//                .from(player)
//                .leftJoin(player.team, team)
//                .where(player.position.eq("GK"), player.team.regionName.eq(team.regionName))
//                .fetch();
//
//        return ls.stream().map(tuple -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("position", tuple.get(player.position));
//            map.put("teamId", tuple.get(player.team.teamId));
//            return map;
//        }).collect(Collectors.toList());


    // 8  O
    @Override
    public List<Map<String, String>> getOnByHeightAndWeightDSL(String regionName) {
        String seoulTeamId = jpaQueryFactory.select(team.teamId)
                .from(team)
                .where(team.regionName.eq("seoul"))
                .fetchFirst();
        return jpaQueryFactory.select(
                        player.height.coalesce("0").as("height"),
                        player.weight.coalesce("0").as("weight"),
                        player.playerName
                ).from(player)
                .where(player.team.teamId.eq(seoulTeamId))
                .fetch().stream().map(i -> Map.of("playerName",i.get(player.playerName),
                        "height", i.get(ExpressionUtils.as(player.height.coalesce("0"), "height")),
                        "weight", i.get(ExpressionUtils.as(player.weight.coalesce("0"), "weight"))
                )).toList();
    }

    // 9
    @Override
    public List<PlayerDto> getPlayerInfoByRegionDSL() {
        return null;
    }

    // 10 O
    @Override
    public List<PlayerDto> getOnByPositionAndTeamId10DSL() {
        return null;
    }

    // 18
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

    // 20 서브쿼리

    @Override
    public List<PlayerDto> getByOnPositionAndTeamId20DSL() {
        return null;
    }
    // 21 서브쿼리
    @Override
    public List<PlayerDto> getOnByPositionAndTeamId21DSL() {
        return null;
    }
    // 22 서브쿼리
    @Override
    public List<PlayerDto> getHeightAndTeamIdDSL() {
       return null;
    }
}
