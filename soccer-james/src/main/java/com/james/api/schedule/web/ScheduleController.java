package com.james.api.schedule.web;

import com.james.api.schedule.model.ScheduleDto;
import com.james.api.schedule.service.ScheduleServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/schedules")
@Slf4j
public class ScheduleController {
    private final ScheduleServiceImpl service;
    private final ScheduleRouter router;
    @GetMapping("/search")
    public ResponseEntity<List<ScheduleDto>> searchSchedule(
            @RequestParam(value = "q",required = true) String q,
            @RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String endDate
    ){
        log.info("Controller Search Schedule {}, {}, {}",q,startDate,endDate);
        List<ScheduleDto> list=router.execute(q,startDate,endDate);
        return ResponseEntity.ok(list);
    }
}