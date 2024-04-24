package com.klc.daloopintegration.resources;

import com.klc.daloopintegration.exception.ErrorHandling;
import com.klc.daloopintegration.model.ConnectivityDTO;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.services.ConnectivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ConnectivityController {

    @Autowired
    private ConnectivityService connectivityService;

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

        return ResponseEntity.status(200).body(this.connectivityService.getAllByStationId(stationId));
    }
}
