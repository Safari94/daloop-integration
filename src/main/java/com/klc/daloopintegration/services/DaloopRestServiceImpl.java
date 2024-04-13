package com.klc.daloopintegration.services;


import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.dto.AuthResponseDTO;
import com.klc.daloopintegration.dto.ChargingActivityDataDTO;
import com.klc.daloopintegration.dto.UsageBreakdownDTO;
import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.entities.SessionInfo;
import com.klc.daloopintegration.mappers.UsageBreakdownMapper;
import com.klc.daloopintegration.repository.HookRepository;
import com.klc.daloopintegration.repository.SessionRepository;
import com.klc.daloopintegration.repository.UsageBreakdownRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DaloopRestServiceImpl implements DaloopRestService {

    private static final String BASE_PATH_AUTH="https://mobime.io";

    @Autowired
    private HookRepository hookRepository;

    @Autowired
    private UsageBreakdownMapper usageBreakdownMapper;

    @Autowired
    private UsageBreakdownRepository usageBreakdownRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private String getToken(){

        String formData = "grant_type=client_credentials&client_id=KLC_MIDDLEWARE_CLIENT&client_secret=476faaea-5e27-4968-bb1c-d2ebf0c5d255&scope=openid";


        WebClient webClient = WebClient.create();

        Mono<AuthResponseDTO> result  = webClient.post()
                .uri("https://mobime.io/auth/realms/KLC/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(formData))
                .retrieve()
                .bodyToMono(AuthResponseDTO.class);

        AuthResponseDTO resp = result.block();
        log.info("Token: "+ resp.toString());

        return resp!=null ? resp.getAccessToken() : "";

    }

    public UsageBreakdownDTO getTransactionsDetails(String transactionId){

        RestClient restClient = RestClient.create();

        String token = getToken();
        log.info(token);
        /*
        WebClient webClient = WebClient.create();
        Mono<String> responseMono = webClient.get()
                .uri(BASE_PATH_AUTH+"/mcp/api/detail/charging-activity?filter=id=="+transactionId)
                .header("Authentication","Bearer "+token)
                .header("BUSINESS_UNIT","KLC")// Specify the endpoint
                .retrieve() // Retrieve the response body
                .bodyToMono(String.class); // Convert the response body to a Mono<String>



        return responseMono.block();
        */

        UsageBreakdownDTO response = restClient.get()
                .uri("https://mobime.io//api/mcp/breakdown/usage/"+transactionId)
                .header("Authorization","Bearer "+token)
                .header("BUSINESS_UNIT","KLC")
                .retrieve()
                .body(UsageBreakdownDTO.class);

        if(response!=null){
            log.info("[CREATE] - Insert new usage breakdown info");
            this.usageBreakdownRepository.save(this.usageBreakdownMapper.dtoToEntity(response));
        }

        return response;
    }

    @Override
    public void registerConnectivityEvent(HookData hookTemplate) {

        Hook hook = new Hook();
        hook.setEventType(hookTemplate.getEvent());
        hook.setStationInfo(hookTemplate.getData().getChargingStationId());
        hook.setConnectivity(hookTemplate.getData().getConnectivity());
        hook.setCreatedDate(LocalDateTime.now());

        this.hookRepository.save(hook);

    }

    public UUID storeStartTransaction(HookData hookTemplate){

        SessionInfo si = new SessionInfo();
        si.setTransactionId(hookTemplate.getData().getUsageId());
        si.setEndTime(null);
        si.setStartTime(LocalDateTime.now());

        UUID savedId = this.sessionRepository.save(si).getId();


        return savedId;

    }

    public void endTransaction(HookData hookTemplate){

        SessionInfo s = this.sessionRepository.findByTransactionIdAndEndTimeIsNull(hookTemplate.getData().getUsageId());
        if(s!=null){
            s.setEndTime(LocalDateTime.now());
            this.sessionRepository.save(s);
        }

    }

}
