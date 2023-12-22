package com.klc.daloopintegration.resources;


import com.klc.daloopintegration.data.HookData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@Slf4j
public class WebhookController {



    @PostMapping("/api/webhook")
    public ResponseEntity<?> hookHandling(@RequestHeader("Authorization") String authorization, @RequestBody HookData hookTemplate){
        log.info(hookTemplate.toString());

        if(authorization.equals("")){

            return switch (hookTemplate.getEvent()) {
                case "started", "costCalculated", "ended" ->

                    //Get season data
                        ResponseEntity.status(200).body(Collections.singletonMap("body", "ok"));
                default -> ResponseEntity.status(400).body(Collections.singletonMap("result", "event unknown"));
            };


        }else{
            return ResponseEntity.status(403).body(Collections.singletonMap("result","invalid token"));
        }



    }
}
