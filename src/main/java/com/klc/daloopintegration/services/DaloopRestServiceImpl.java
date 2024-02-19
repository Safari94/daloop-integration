package com.klc.daloopintegration.services;


import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.dto.AuthResponseDTO;
import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.repository.HookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class DaloopRestServiceImpl implements DaloopRestService {

    private static final String BASE_PATH_AUTH="https://mobime.io";

    @Autowired
    private HookRepository hookRepository;

    private String getToken(){

        String formData = "grant_type=client_credentials&client_id=KLC_MIDDLEWARE_CLIENT&client_secret=6a7df6f5-7412-4dc9-af51-ee2dc02c6c4b&scope=openid";


        WebClient webClient = WebClient.create();

        Mono<AuthResponseDTO> result  = webClient.post()
                .uri(BASE_PATH_AUTH+"/auth/realms/KLC/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(formData))
                .retrieve()
                .bodyToMono(AuthResponseDTO.class);

        AuthResponseDTO resp = result.block();

        return resp!=null ? resp.getAccessToken() : "";

    }

    public String getTransactionsDetails(String transactionId){

        String token = getToken();

        WebClient webClient = WebClient.create();
        Mono<String> responseMono = webClient.get()
                .uri(BASE_PATH_AUTH+"/analytics-smart/api/detail/charging-activity?filter=id=="+transactionId)
                .header("Authentication","Bearer "+getToken())// Specify the endpoint
                .retrieve() // Retrieve the response body
                .bodyToMono(String.class); // Convert the response body to a Mono<String>



        return responseMono.block();
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
