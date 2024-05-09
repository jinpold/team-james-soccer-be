package com.james.api.common.model;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@NoArgsConstructor
@Builder
@Log4j2
@AllArgsConstructor
public class PageDto {

    // o
    private Long pageSize; // 한 페이지당 보여지는 수 (변경가능)
    // x
    public static final Long BLOCK_SIZE = 10L; // 대문자 상수 & 소문자 변수 (되도록 변경 X)

    // x
    private Long totalCount; //All row
    private Long blockCount; //block
    private Long pageCount; //page


    // x   page
    private Long startRow; // row 시작
    private Long endRow;


    // o
    private Long startPage;
    private Long endPage;

    // x    block 단위  전 / 후   <  |  > UI 페이지 기준
    private Long prevBlock;
    private Long nextBlock;


    private Long blockNumber;

    // o
    private Long pageNumber; // 페이지 시작 (페이지 시작을 알면 row 시작 지점을 알 수 있음)

    // o
    private boolean existPrev;
    private boolean existNext;

}
