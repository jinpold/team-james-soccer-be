package com.james.api.player.repository;
import com.james.api.player.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@PersistenceContext // 전역  -> scope가 꼬이지 않게  전역에서 주입해주는게 베스트
public class PlayerDAOImpl implements PlayerDAO { // 서비스임플 느낌 -> 구현체 -오버라이딩으로 기능 구현

    @PersistenceContext // 필드 주입 (가능 / 삭제에정)
    private final EntityManager entityManager; // 스프링에서 불러옴.
    @Override
    @PersistenceContext // 로컬 주입 (가능 / 삭제에정)
    public Player p(Player player, Long id) {
        return entityManager.find(Player.class, 1L);
    }

    @Override
    public Player p2(Player player, Long id) {
        entityManager.createQuery("select a from players a where a.id = :id", Player.class);
        return entityManager.find(Player.class, 1L);
    }

    @Override
    public void insert(Player player) {
        entityManager.persist(player);
    }
    @Override
    public void update(Player player) {
        entityManager.persist(player);
    }
}

// 3가지  현주씨한테 다시 설명 한번 듣기