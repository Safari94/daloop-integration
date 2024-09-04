package com.klc.daloopintegration.resources;

import com.klc.daloopintegration.exception.ErrorHandling;
import com.klc.daloopintegration.model.ConnectivityDTO;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.services.ConnectivityService;
import com.klc.daloopintegration.services.InfraspeakService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@CrossOrigin
public class ConnectivityController {

    @Autowired
    private ConnectivityService connectivityService;

    @Autowired
    private InfraspeakService infraspeakService;

    @Operation(summary = "Get all connectivities records by station id ", description = "station must exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ConnectivityDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorHandling.class)) }) })

    @GetMapping("/api/v1/connectivity/{station_id}/get_all")
    public ResponseEntity<?> getAllUsageBreakdownByUsage(@PathVariable("station_id") String stationId){

        try {
            return ResponseEntity.status(200).body(this.connectivityService.getAllByStationId(stationId));
        }
        catch (Exception ex){

            return ResponseEntity.status(500).body(Collections.singletonMap("error_message",ex.toString()));
        }

    }


    @Operation(summary = "Get all connectivities records by station id between dates ", description = "station must exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ConnectivityDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorHandling.class)) }) })

    @GetMapping("/api/v1/connectivity/{station_id}/get_between_dates")
    public ResponseEntity<?> getByStationIdBetweenDates(@PathVariable("station_id") String stationId, @RequestParam("start_date") LocalDateTime startDate, @RequestParam("end_date") LocalDateTime endDate){

        try {
            return ResponseEntity.status(200).body(this.connectivityService.getAllByStationIdBetweenDates(stationId,startDate,endDatex));
        }
        catch (Exception ex){

            return ResponseEntity.status(500).body(Collections.singletonMap("error_message",ex.toString()));
        }


    }






}
