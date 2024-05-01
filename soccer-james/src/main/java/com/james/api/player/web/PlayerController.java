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
        @RequestParam(value = "playerName", required = false) String playerName,
        @RequestParam(value = "teamId", required = false) String teamId)

    {
        log.info("Controller searchPlayer q is {}", q);
        List<Map<String, Object>> object = playerRouter.execute(q);

        return ResponseEntity.ok(object);
    }
}
