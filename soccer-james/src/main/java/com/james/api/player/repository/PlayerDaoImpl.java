package com.james.api.player.repository;
import com.james.api.player.model.PlayerDto;
import com.james.api.player.model.QPlayer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class PlayerDaoImpl implements PlayerDao {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PlayerDto> getAllPlayers() {
        return jpaQueryFactory.select(
                        QPlayer.player.id,
                        QPlayer.player.playerId,
                        QPlayer.player.name,
                        QPlayer.player.playerName,
                        QPlayer.player.ePlayerName,
                        QPlayer.player.nickname,
                        QPlayer.player.joinYyyy,
                        QPlayer.player.position,
                        QPlayer.player.backNo,
                        QPlayer.player.nation,
                        QPlayer.player.birthDate,
                        QPlayer.player.solar,
                        QPlayer.player.height,
                        QPlayer.player.weight,
                        QPlayer.player.regDate,
                        QPlayer.player.modDate,
                        QPlayer.player.team.teamId)
                .from(QPlayer.player)
                .fetch()
                .stream()
                .map(tuple -> PlayerDto.builder()
                        .id(tuple.get(QPlayer.player.id))
                        .playerId(tuple.get(QPlayer.player.playerId))
                        .name(tuple.get( QPlayer.player.name))
                        .playerName(tuple.get(QPlayer.player.playerName))
                        .ePlayerName(tuple.get(QPlayer.player.ePlayerName))
                        .nickname(tuple.get(QPlayer.player.nickname))
                        .joinYyyy(tuple.get(QPlayer.player.joinYyyy))
                        .position(tuple.get(QPlayer.player.position))
                        .backNo(tuple.get(QPlayer.player.backNo))
                        .nation(tuple.get(QPlayer.player.nation))
                        .birthDate(tuple.get(QPlayer.player.birthDate))
                        .solar(tuple.get(QPlayer.player.solar))
                        .height(tuple.get(QPlayer.player.height))
                        .weight(tuple.get(QPlayer.player.weight))
                        .solar(tuple.get(QPlayer.player.solar))
                        .regDate(tuple.get(QPlayer.player.regDate))
                        .modDate(tuple.get(QPlayer.player.modDate))
                        .build()).toList();
    }
}