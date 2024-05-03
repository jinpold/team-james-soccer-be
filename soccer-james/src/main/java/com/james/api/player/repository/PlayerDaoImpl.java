package com.james.api.player.repository;
import com.james.api.player.model.Player;
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
        jpaQueryFactory.select(new QPlayer(QPlayer.player.id, QPlayer.player.playerName))
                .from(QPlayer.player)
                .fetch()
                .stream()
                .entityToDto(Player.class);
        return null;

    }
}