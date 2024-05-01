package com.james.api.schedule;

import com.james.api.schedule.model.ScheduleDto;
import com.james.api.schedule.service.ScheduleServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Slf4j
public class ScheduleController {
    private final ScheduleServiceImpl service;
    @GetMapping("/search/getInfoInScheduleByDate")
    public ResponseEntity<List<ScheduleDto>> getInfoInScheduleByDate(@RequestParam("date") String date){
        return ResponseEntity.ok(service.getInfoInScheduleByDate(date));
    }

    @GetMapping("/search/getInfoInScheduleByScoreGap")
    public ResponseEntity<List<ScheduleDto>> getInfoInScheduleByScoreGap(@RequestParam("score") String score){
        return ResponseEntity.ok(service.getInfoInScheduleByScoreGap(score));
    }
}