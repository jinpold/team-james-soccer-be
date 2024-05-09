package com.james.api.stadium.web;

import com.james.api.common.model.Box;
import com.james.api.common.model.PageDto;
import com.james.api.common.serviceImpl.PageServiceImpl;
import com.james.api.stadium.model.StadiumDto;
import com.james.api.stadium.service.StadiumServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/stadiums")
@Slf4j
public class StadiumController {
    private final StadiumServiceImpl service;
    private final StadiumRouter router;
    private final PageServiceImpl pageService;
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "q",required = true) String q,
            @RequestParam(value = "regionName",required = false) String regionName,
            @RequestParam(value = "date",required = false) String date,
            @RequestParam(value = "teamName",required = false) String teamName,
            @RequestParam(value = "position",required = false) String position,
            @RequestParam(value = "score",required = false) String score,
            Pageable pageable
    ){
        log.info("Controller Search Stadium: {}",q);

        log.info("MY-INFO : Controller searchPlayer q is {}", q);
        log.info("MY-INFO : Controller searchPlayer page is {}", pageable.getPageNumber());
        log.info("MY-INFO : Controller searchPlayer limit is {}", pageable.getPageSize());
        log.info("MY-INFO : Controller searchPlayer sortField is {}", pageable.getSort());

        PageDto page=pageService.getPageDto(service.countAll(), (long) pageable.getPageSize(), (long) pageable.getPageNumber());

        List<StadiumDto> o=router.execute(q,regionName,date,teamName,position,score);

        log.info("MY-INFO : Controller searchPlayer totalCount is {}", page.getTotalCount());
        log.info("MY-INFO : Controller searchPlayer pageCount is {}", page.getPageCount());
        log.info("MY-INFO : Controller searchPlayer blockCount is {}", page.getBlockCount());
        log.info("MY-INFO : Controller searchPlayer startRow is {}", page.getStartRow());
        log.info("MY-INFO : Controller searchPlayer endRow is {}", page.getEndRow());
        log.info("MY-INFO : Controller searchPlayer blockNumber is {}", page.getBlockNumber());
        log.info("MY-INFO : Controller searchPlayer startPage is {}", page.getStartPage());
        log.info("MY-INFO : Controller searchPlayer endPage is {}", page.getEndPage());
        log.info("MY-INFO : Controller searchPlayer existPrev is {}", page.isExistPrev());
        log.info("MY-INFO : Controller searchPlayer existNext is {}", page.isExistNext());

        Box box=new Box();
        box.setList(o);
        box.setPageDto(page);

        return ResponseEntity.ok(box);
    }
}