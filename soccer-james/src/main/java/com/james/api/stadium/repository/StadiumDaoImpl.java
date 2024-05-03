package com.james.api.stadium.repository;
import com.james.api.stadium.model.QStadium;
import com.james.api.stadium.model.StadiumDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RequiredArgsConstructor
public class StadiumDaoImpl implements StadiumDao{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<StadiumDto> getAllStadiums() {
        return jpaQueryFactory.select(
                        QStadium.stadium.id,
                        QStadium.stadium.stadiumId,
                        QStadium.stadium.stadiumName,
                        QStadium.stadium.hometeamId,
                        QStadium.stadium.seatCount,
                        QStadium.stadium.address,
                        QStadium.stadium.ddd,
                        QStadium.stadium.tel,
                        QStadium.stadium.regDate,
                        QStadium.stadium.modDate)
                .from(QStadium.stadium)
                .fetch()
                .stream()
                .map(tuple -> StadiumDto.builder()
                        .id(tuple.get(QStadium.stadium.id))
                        .stadiumId(tuple.get(QStadium.stadium.stadiumId))
                        .stadiumName(tuple.get(QStadium.stadium.stadiumName))
                        .hometeamId(tuple.get(QStadium.stadium.hometeamId))
                        .seatCount(tuple.get(QStadium.stadium.seatCount))
                        .address(tuple.get(QStadium.stadium.address))
                        .ddd(tuple.get(QStadium.stadium.ddd))
                        .tel(tuple.get(QStadium.stadium.tel))
                        .regDate(tuple.get(QStadium.stadium.regDate))
                        .modDate(tuple.get(QStadium.stadium.modDate))
                        .build()).toList();
    }
}
