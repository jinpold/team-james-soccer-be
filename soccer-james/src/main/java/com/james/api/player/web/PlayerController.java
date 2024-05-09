package com.james.api.player.web;

import com.james.api.common.model.Box;
import com.james.api.common.model.PageDto;
import com.james.api.common.service.PageService;
import com.james.api.player.service.PlayerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/players")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerRouter playerRouter;
    private final PlayerService playerService;
    private final PageService pageService;

    @GetMapping(path="/search")
    public ResponseEntity <List<?>> searchPlayer(
        @RequestParam(value="q", required = true) String q,  Pageable pageable,
        @RequestParam(value = "position", required = false) String position,
        @RequestParam(value = "regionName", required = false) String regionName,
        @RequestParam(value = "teamId1", required = false) String teamId1,
        @RequestParam(value = "teamId2", required = false) String teamId2,
        @RequestParam(value = "height1", required = false) String height1,
        @RequestParam(value = "height2", required = false) String height2,
        @RequestParam(value = "playerName", required = false) String playerName,
        @RequestParam(value = "teamName1", required = false) String teamName1,
        @RequestParam(value = "teamName2", required = false) String teamName2,
        @RequestParam(value = "limit", required = false) Integer limit)

    {
        log.info("MY-INFO : Controller searchPlayer q is {}", q);
        log.info("MY-INFO : Controller searchPlayer page is {}", pageable.getPageNumber());
        log.info("MY-INFO : Controller searchPlayer limit is {}", pageable.getPageSize());
        log.info("MY-INFO : Controller searchPlayer sortField is {}", pageable.getSort().toString());

        // nowPage, rowCount, pageSize, blockSize 외부주입.. count, size 1 부터, number 는 0부터


        List<?> list = playerRouter.execute
                (q,position, teamId1, teamId2, regionName, height1, height2,playerName,teamName1, teamName2, limit);

        Long totalCount = playerRouter.countAllplayers();
        Long pageNumber = (long) pageable.getPageNumber();
        Long pageSize = (long) pageable.getPageSize();

        PageDto page = pageService.getPageDto(totalCount,pageNumber,pageSize);

        Box box = new Box();
        box.setPageDto(page);
        box.setList(list);

        return ResponseEntity.ok(box.getList());
    }
}

