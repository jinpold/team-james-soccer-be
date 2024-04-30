package com.james.api.player.model;
import com.james.api.common.BaseEntity;
import com.james.api.team.model.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "players")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "id")
public class Player extends BaseEntity {
    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerId;
    private String name;
    private String playerName;
    private String ePlayerName;
    private String nickname;
    private String joinYyyy ;
    private String position ;
    private String backNo ;
    private String nation ;
    private String birthDate ;
    private String solar ;
    private Integer height ;
    private Integer weight ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true, referencedColumnName = "teamId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Team team;
}

