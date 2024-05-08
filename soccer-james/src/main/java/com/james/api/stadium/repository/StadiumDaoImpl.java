package com.james.api.stadium.repository;
import com.james.api.player.model.QPlayer;
import com.james.api.schedule.model.QSchedule;
import com.james.api.stadium.model.QStadium;
import com.james.api.stadium.model.QStadiumDto;
import com.james.api.stadium.model.StadiumDto;
import com.james.api.team.model.QTeam;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.james.api.team.model.QTeam.team;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StadiumDaoImpl implements StadiumDao{

    private final JPAQueryFactory jpaQueryFactory;
    private final QStadium stadium=QStadium.stadium;
    private final QTeam team=QTeam.team;
    private final QSchedule schedule=QSchedule.schedule;
    private final QPlayer player=QPlayer.player;

    @Override
    public List<StadiumDto> getStadiumNameWithTeam(@Param("regionName") String regionName) {
        return jpaQueryFactory.select(
                new QStadiumDto(
                        stadium.stadiumName.as("stadiumName")
                        ,team.regionName.concat("[ ]").concat(team.teamName).as("teamName"))
                )
                .from(stadium)
                .join(stadium.team,team)
                .where(team.regionName.eq(regionName))
                .where(stadium.stadiumName.like(regionName+"%"))
                .orderBy(stadium.stadiumName.asc())
//                .offset(1)      //페이지 번호 - pagination
//                .limit(5)       //한 페이지 당 보여지는 row 수 - pagination
                .fetch();

    }

    @Override
    public List<StadiumDto> getStadiumAndTeamAndSchedule(@Param("date") String date) {
        return jpaQueryFactory.select(new QStadiumDto(
                team.teamName.as("teamName"),stadium.stadiumName.as("stadiumName"),
                        JPAExpressions.select(team.teamName.as("awayteamName"))
                                .from(team)
                                .distinct()
                                .where(schedule.awayteamId.eq(team.teamId))
        )).distinct()
                .from(stadium)
                .join(stadium.team, team)
                .join(stadium.schedule, schedule)
                .where(schedule.scheDate.eq(date))
                .fetch();
    }

    @Override
    public List<StadiumDto> getPohangSteelersGk(@Param("date") String date, @Param("teamName") String teamName, @Param("position") String position) {
                return jpaQueryFactory.select(new QStadiumDto(
                        player.playerName.as("playerName"),player.position.as("position"),
                        team.teamName.concat(" ").concat(team.regionName).as("teamName"),
                        stadium.stadiumName.as("stadiumName"),schedule.scheDate.as("scheDate")
                )).distinct()
                .from(stadium)
                .join(stadium.schedule,schedule)
                .join(stadium.team, team)
                .join(team.player, player)
                .where(player.position.eq(position))
                .where(team.teamName.eq(teamName))
                .where(schedule.scheDate.eq(date))
                .fetch();
    }

    @Override
    public List<StadiumDto> getHomeTeamWin(@Param("score") String score) {

        return jpaQueryFactory.select(new QStadiumDto(
                stadium.stadiumName.as("stadiumName"),schedule.scheDate.as("scheDate"),
                        JPAExpressions.select(team.teamId.as("hometeamName")).from(team).where(team.teamId.eq(schedule.hometeamId)),
                        JPAExpressions.select(team.teamId.as("awayteamName")).from(team).where(team.teamId.eq(schedule.awayteamId))
         )).from(stadium)
                .join(stadium.schedule,schedule)
                .join(stadium.team,team)
                .where(schedule.homeScore.subtract(schedule.awayScore).goe(Integer.parseInt(score)))
                .fetch();
    }

    @Override
    public List<StadiumDto> getNoHomeTeam() {
                return jpaQueryFactory.select(new QStadiumDto(
                stadium.stadiumName.as("stadiumName"),
                team.teamName.as("teamName")
                )).from(stadium)
                .leftJoin(stadium.team,team)
                .fetch();
    }

    @Override
    public Long countAll() {
        return Long.parseLong(String.valueOf(jpaQueryFactory.select(stadium.count())));
    }


}
