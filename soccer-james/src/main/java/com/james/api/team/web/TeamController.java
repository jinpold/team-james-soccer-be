package com.james.api.team.web;

import com.james.api.team.model.TeamDto;
import com.james.api.team.service.TeamServiceImpl;
import com.querydsl.core.Tuple;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping("/api/teams")
@Slf4j
public class TeamController {
    private final TeamServiceImpl service;
    private final TeamRouter router;

    @GetMapping("/search")
    public ResponseEntity<List<?>> search(
            @RequestParam(value = "q", required = true) String q, Pageable pageable) {
        log.info("MY-INFO : Controller searchPlayer q is {}", q);
        log.info("MY-INFO : Controller searchPlayer page is {}", pageable.getPageNumber());
        log.info("MY-INFO : Controller searchPlayer limit is {}", pageable.getPageSize());
        log.info("MY-INFO : Controller searchPlayer sortField is {}", pageable.getSort().toString());

        // nowPage, rowCount, pageSize, blockSize 외부주입.. count, size 1 부터, number 는 0부터

        int totalCount = Math.toIntExact(service.countAllTeams());  // rowCount
        int blockCount = 0; // totalCount%(BLOCK_SIZE*pageSize) (나머지가 0 이면 몫, 나머지 있으면 몫+1)
        int pageCount = 0;  // totalCount%pageSize (나머지가 0 이면 몫, 나머지 있으면 몫+1)

        int startRow = 0;   // page
        int endRow = 0;     // page

        int startPage = 0;
        int endPage = 0;

        int nextBlock = 0;
        int prevBlock = 0;

        int blockNumber = 0;
        int pageNumber = 0;     // 디폴트 1 = 시작 숫자 1

        int pageSize = 10;
        int BLOCK_SIZE = 10;    // 대문자 상수, 소문자 변수

        boolean existPrev = false;  // true/false로 전후 페이지 구분
        boolean existNext = false;

        blockCount = totalCount % (BLOCK_SIZE * pageSize) == 0 ? totalCount / (BLOCK_SIZE * pageSize) : totalCount / (BLOCK_SIZE * pageSize) + 1;
        pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        startRow = (pageNumber+1) * pageSize - pageSize;  // 1~10 11~20 ...
        endRow = startRow + pageSize;
        startPage = (blockNumber+1) * (BLOCK_SIZE) - (BLOCK_SIZE - 1); // 1
        endPage = startPage + (BLOCK_SIZE - 1); // 10 20 30 40

        blockNumber = (pageNumber+1) / BLOCK_SIZE; // 11 / 10 = 1
        nextBlock = blockNumber+1;
        prevBlock = blockNumber!=0 ? blockNumber-1 : blockNumber;

        existNext = blockNumber < blockCount;
        existPrev = blockNumber > 0;


        log.info("MY-INFO : Controller searchPlayer totalCount is {}", totalCount);
        log.info("MY-INFO : Controller searchPlayer pageCount is {}", pageCount);
        log.info("MY-INFO : Controller searchPlayer blockCount is {}", blockCount);
        log.info("MY-INFO : Controller searchPlayer startRow is {}", startRow);
        log.info("MY-INFO : Controller searchPlayer endRow is {}", endRow);
        log.info("MY-INFO : Controller searchPlayer blockNum is {}", blockNumber);
        log.info("MY-INFO : Controller searchPlayer startPage is {}", startPage);
        log.info("MY-INFO : Controller searchPlayer endPage is {}", endPage);
        log.info("MY-INFO : Controller searchPlayer existPrev is {}", existPrev);
        log.info("MY-INFO : Controller searchPlayer existNext is {}", existNext);
        log.info("MY-INFO : Controller searchPlayer nextBlock is {}", nextBlock);
        log.info("MY-INFO : Controller searchPlayer prevBlock is {}", prevBlock);

        List<?> list = router.execute(q, pageable.getPageNumber(), pageable.getPageSize());
//        PageDto page = null;
//        Box box = new Box();
//        box.setPageDto(page);
//        box.setList(ls);
//        프론트에서 백의 box만 본다
//        박스 내부에는 dto가 들어간다
        return ResponseEntity.ok(list); // ~.ok(box)
    }
}