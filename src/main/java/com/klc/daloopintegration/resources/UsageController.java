package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.exception.ErrorHandling;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.services.UsageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
