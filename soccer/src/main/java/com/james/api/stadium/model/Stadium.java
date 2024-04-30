package com.james.api.stadium.model;
import com.james.api.common.BaseEntitiy;
import com.james.api.schedule.model.Schedule;
import com.james.api.team.model.Team;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "stadiums")
@ToString(exclude = {"id"})
public class Stadium extends BaseEntitiy {

   @Id
   @Column(name = "id", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String stadiumId;
   private String stadiumName;
   private String hometeamId;
   private Integer seatCount;
   private String address;
   private String ddd;
   private String tel;

   @OneToMany(mappedBy = "stadium", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Team> team;

   @OneToMany(mappedBy = "stadium", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Schedule> schedule;
}