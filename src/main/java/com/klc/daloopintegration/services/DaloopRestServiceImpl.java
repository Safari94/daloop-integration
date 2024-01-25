package com.klc.daloopintegration.services;


import com.klc.daloopintegration.dto.AuthResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class DaloopRestServiceImpl implements DaloopRestService {

    private static final String BASE_PATH_AUTH="https://mobime.io";

    private String getToken(){

        String formData = "grant_type=client_credentials&client_id=API_GATEWAY_MCP&client_secret=6b8fed4e-7021-4f0b-89e4-d3fe94378d5a&scope=openid";


        WebClient webClient = WebClient.create();

        Mono<AuthResponseDTO> result  = webClient.post()
                .uri(BASE_PATH_AUTH+"/auth/realms/LIDL_PT/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(formData))
                .retrieve()
                .bodyToMono(AuthResponseDTO.class);

        AuthResponseDTO resp = result.block();

        return resp!=null ? resp.getAccessToken() : "";

    }

    public String getTransactionsDetails(){


        return getToken();
    }
}
