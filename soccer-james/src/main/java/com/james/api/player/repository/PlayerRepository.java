package com.james.api.player.repository;
import com.james.api.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerJpqlRepository {

}
