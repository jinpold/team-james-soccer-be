package com.james.api.team.web;
import com.james.api.team.service.TeamService;
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
@RequestMapping(path = "/api/team")
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    private final TeamRouter teamRouter;

    @GetMapping(path="/search")
    public ResponseEntity <List<Map<String, Object>>> searchTeam(
            @RequestParam(value="q", required = true) String q)

    {
        log.info("Controller searchPlayer q is {}", q);
        List<Map<String, Object>> object = teamRouter.execute(q);

        return ResponseEntity.ok(object);
    }
}
