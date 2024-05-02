package com.james.api.team.web;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping("/api/teams")
@Slf4j
public class TeamController {

    private final TeamRouter router;
    @GetMapping("/search")
    public ResponseEntity<List<Map<String,Object>>> search(
            @RequestParam(value = "q",required = true) String q
    ){
        log.info("Controller Search Team: {}",q);
        List<Map<String,Object>> list=router.execute(q);
        return ResponseEntity.ok(list);
    }
}