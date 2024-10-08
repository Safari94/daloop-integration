package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.services.DaloopRestService;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@Hidden
@Slf4j
public class WebhookController {

    @Autowired
    public DaloopRestService daloopRestService;



    @PostMapping("/api/webhook")
    public ResponseEntity<?> hookHandling(@RequestHeader("API_KEY") String apiKey, @RequestBody HookData hookTemplate){
        log.info(hookTemplate.toString());


        if(apiKey!=null){

            log.info(apiKey);
            return switch (hookTemplate.getEvent()) {
                case "started" -> {

                    UUID saved=this.daloopRestService.storeStartTransaction(hookTemplate);
                    log.info("Register new transaction -> {}",saved);
                    yield ResponseEntity.status(200).body(Collections.singletonMap("result", "ok"));

                }
                case  "ended" -> {
                    this.daloopRestService.endTransaction(hookTemplate);
                    yield ResponseEntity.status(200).body(Collections.singletonMap("result", "ok"));
                }
                case "costCalculated" -> {
                    UsageBreakdownDTO res = this.daloopRestService.getTransactionsDetails(hookTemplate.getData().getUsageId());
                    log.info(String.valueOf(res));
                    yield ResponseEntity.status(200).body(Collections.singletonMap("result", "ok"));
                }
                case "connectivity" -> {
                         this.daloopRestService.registerConnectivityEvent(hookTemplate);
                        yield ResponseEntity.status(200).body(Collections.singletonMap("result", "ok"));

                    }
               default -> ResponseEntity.status(400).body(Collections.singletonMap("result", "event unknown"));
            };
        }else{
            return ResponseEntity.status(403).body(Collections.singletonMap("result","invalid token"));
        }



    }
}
