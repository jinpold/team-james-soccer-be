package com.james.api.stadium.web;

import com.james.api.stadium.model.StadiumDto;
import com.james.api.stadium.service.StadiumServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/search")
    public ResponseEntity<List<Map<String,Object>>> search(
            @RequestParam(value = "q",required = true) String q,
            @RequestParam(value = "regionName",required = false) String regionName,
            @RequestParam(value = "date",required = false) String date,
            @RequestParam(value = "teamName",required = false) String teamName,
            @RequestParam(value = "position",required = false) String position,
            @RequestParam(value = "score",required = false) String score
    ){
        log.info("Controller Search Stadium: {}",q);

        List<Map<String,Object>> o=router.execute(q,regionName,date,teamName,position,score);

        return ResponseEntity.ok(o);
    }
}