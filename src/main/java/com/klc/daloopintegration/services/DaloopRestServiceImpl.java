package com.klc.daloopintegration.services;


import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.dto.AuthResponseDTO;
import com.klc.daloopintegration.dto.ChargingActivityDataDTO;
import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.repository.HookRepository;
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

@Service
@Slf4j
public class DaloopRestServiceImpl implements DaloopRestService {

    private static final String BASE_PATH_AUTH="https://mobime.io";

    @Autowired
    private HookRepository hookRepository;

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

    public ChargingActivityDataDTO getTransactionsDetails(String transactionId){

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

        ChargingActivityDataDTO response = restClient.get()
                .uri("https://mobime.io/api/mcp/analytics/detail/charging-activity?filter=id=="+transactionId)
                .header("Authorization","Bearer "+token)
                .header("BUSINESS_UNIT","KLC")
                .retrieve()
                .body(ChargingActivityDataDTO.class);

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

}
