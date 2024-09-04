package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.exception.ErrorHandling;
import com.klc.daloopintegration.model.ConnectivityDTO;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.services.UsageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@CrossOrigin
public class UsageController {

    @Autowired
    private UsageService usageService;


    @Operation(summary = "Get all session by usage ", description = "Customer must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = UsageBreakdownDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorHandling.class)) }) })

    @GetMapping("/api/v1/usage/get_all_by_usage")
    public ResponseEntity<?> getAllUsageBreakdownByUsage(@RequestParam("usage") String usage){

        return ResponseEntity.status(200).body(this.usageService.getAllUsageBreakdownByUsage(usage));
    }

    @Operation(summary = "Get all sessions records by station id between dates ", description = "station must exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = UsageBreakdownDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorHandling.class)) }) })

    @GetMapping("/api/v1/usage/{station_id}/get_between_dates")
    public ResponseEntity<?> getAllUsageBreakdownByUsageBetweenDates(@PathVariable("station_id") String stationId, @RequestParam("start_date") LocalDateTime startDate, @RequestParam("end_date") LocalDateTime endDate){

        try {
            return ResponseEntity.status(200).body(this.usageService.getAllUsageOfaStationBetweenDates(stationId,startDate,endDate));
        }
        catch (Exception ex){

            return ResponseEntity.status(500).body(Collections.singletonMap("error_message",ex.toString()));
        }

    }
}
