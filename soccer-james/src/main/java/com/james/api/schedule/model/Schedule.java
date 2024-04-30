package com.james.api.schedule.model;
import com.james.api.common.BaseEntity;
import com.james.api.stadium.model.Stadium;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "id")
public class Schedule extends BaseEntity {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheDate;
    private String gubun;
    private String hometeamId;
    private String awayteamId;
    private Integer homeScore;
    private Integer awayScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = true, referencedColumnName = "stadiumId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Stadium stadium;
}
