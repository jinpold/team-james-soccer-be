package com.james.api.player.web;
import com.james.api.player.service.PlayerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/player")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerRouter playerRouter;
    private final PlayerService playerService;

    @GetMapping(path="/search")
    public ResponseEntity <List<Map<String, Object>>> searchPlayer(
        @RequestParam(value="q", required = true) String q,
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
        log.info("Controller searchPlayer q is {}", q);
        List<Map<String, Object>> object = playerRouter.execute
                (q,position, teamId1, teamId2, regionName, height1, height2,playerName,teamName1, teamName2, limit);

        return ResponseEntity.ok(object);
    }
}
