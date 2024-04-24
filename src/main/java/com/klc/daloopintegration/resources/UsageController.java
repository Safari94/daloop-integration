package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.services.UsageService;
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

    @GetMapping("/api/v1/usage/get_all_by_usage")
    public ResponseEntity<?> getAllUsageBreakdownByUsage(@RequestParam("usage") String usage){

        return ResponseEntity.status(200).body(this.usageService.getAllUsageBreakdownByUsage(usage));
    }
}
