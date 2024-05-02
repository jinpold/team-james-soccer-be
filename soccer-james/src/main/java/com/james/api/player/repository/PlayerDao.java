package com.james.api.player.repository;
import com.james.api.player.model.Player;
import org.springframework.stereotype.Repository;


@Repository  //DAO는 스프링 레파지토리 Name
public interface PlayerDao { // 인터페이스  서비스
    Player p (Player player, Long id);
    Player p2(Player player, Long id);

    void insert(Player player);
    void update(Player player);

}