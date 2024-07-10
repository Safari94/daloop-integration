package com.klc.daloopintegration.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klc.daloopintegration.data.ApiResponse;
import com.klc.daloopintegration.data.TicketBody;
import com.klc.daloopintegration.services.InfraspeakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
@Slf4j
public class InfraspeakServiceImpl implements InfraspeakService {

    @Override
    public String sendTicketInfraspeak(String stationId,String description,Integer problemId) throws IOException, InterruptedException {

        String locationId=getLocationId(stationId);
        if(!locationId.equals("null")){
            String childId=getChildId(locationId);

            log.info(childId);
            postToInfraspeak(childId,description, problemId);
        }

        return locationId;

    }

    private String getLocationId(String stationId) throws IOException, InterruptedException {

        RestClient restClient = RestClient.create();


        ApiResponse apiResponse = restClient.get()
                .uri(URI.create("https://api.infraspeak.com/v3/locations?s_code="+stationId))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxNzkyIiwianRpIjoiYTFjMTA5NWI4MjY3MjZhYWZmYzMzYjA2MjBlMzA5NDI3NDUwMTkwMWE3NDMxYzgyNWMyMzBkOGU2OTRlYWI2NmRkYjUxNGE0NThmMTRlMGQiLCJpYXQiOjE3MTA0MjQyMDUsIm5iZiI6MTcxMDQyNDIwNSwiZXhwIjoyMDI1OTU3MDA1LCJzdWIiOiI5NDQxNCIsInNjb3BlcyI6W10sInV0eXBlIjoiT1BFUkFUT1IiLCJ1dHlwZV9pZCI6ODQ1NTQsIm5hbWUiOiJJbnRlZ3Jhw6fDo28gU2FsZXNmb3JjZSIsImVtYWlsIjoiaW50ZWdyYWNhbytzYWxlc2ZvcmNlQGluZnJhc3BlYWsuY29tIiwiem9uZWluZm8iOiJFdXJvcGUvTGlzYm9uIn0.hfZr5JeFKSDwFt3kfof2wh1TNnCEzmoV3pNd0Ax-I8DYRpzgz3nvx-w-FNQpr-YWck7P58vGEGp-ogWUq0RliqKoq0nZE1fwCxd0Bfc0neQiDgr6xZviow1u16uN3nAnnSmL1SQnkfW284-WoldWCHB541V2L_V8JqunukctGWNz888JM-gRwsDqpiFQG4uc0P1h4ZY9UELcW_h4d1WXR1VEPFZ2JHt1fZLids3wIeClkLr3kKX8eh0QsA0SshF_-lNFLNe0g96B4r56TSDsais14BiQhgb4R_FMDz2rFpjY7Nh37Siiw6VcBTBHBmu3ngXlmCbO0wDwpnK22yFl0PlKa8UZVsm1Fc3_OSDtuwawLYV1azZPFYAMw6JaGjDrfUiSmN2k_GzZ2VdOxHGG01cXc_0jJZ40AUmdasrIEdIy-XwW1_VvUFpgJvBkfGFHMx45HwLtWcAq7RROCt6AM2o342d3vPFR-QFVG_HunRBF3Yit3zl5t2w-43P2dQlIffcINdWyzAxh3GUe3iGTpw03ySnCEJ6FkLPuHPKVWqJxYsjeKkz82nlasKxkte1DUYNOBjN4FwiM3xZjNrAgGvEIxzaNP-JPRo2DqYV4MpBhKcolGhWK4yfty8B5LeQDO5r5C7eCcu5xkbScxxkyg_Ok2WdhH5na7_4g0OjY5RU")
                .retrieve()
                .body(ApiResponse.class);

        log.info(apiResponse.toString());



        return apiResponse.getData().isEmpty()? "null" : apiResponse.getData().get(0).getId();




    }


    private String getChildId(String locationId){

        RestClient restClient = RestClient.create();


        ApiResponse apiResponse = restClient.get()
                .uri(URI.create("https://api.infraspeak.com/v3/locations/"+locationId+"/children"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxNzkyIiwianRpIjoiYTFjMTA5NWI4MjY3MjZhYWZmYzMzYjA2MjBlMzA5NDI3NDUwMTkwMWE3NDMxYzgyNWMyMzBkOGU2OTRlYWI2NmRkYjUxNGE0NThmMTRlMGQiLCJpYXQiOjE3MTA0MjQyMDUsIm5iZiI6MTcxMDQyNDIwNSwiZXhwIjoyMDI1OTU3MDA1LCJzdWIiOiI5NDQxNCIsInNjb3BlcyI6W10sInV0eXBlIjoiT1BFUkFUT1IiLCJ1dHlwZV9pZCI6ODQ1NTQsIm5hbWUiOiJJbnRlZ3Jhw6fDo28gU2FsZXNmb3JjZSIsImVtYWlsIjoiaW50ZWdyYWNhbytzYWxlc2ZvcmNlQGluZnJhc3BlYWsuY29tIiwiem9uZWluZm8iOiJFdXJvcGUvTGlzYm9uIn0.hfZr5JeFKSDwFt3kfof2wh1TNnCEzmoV3pNd0Ax-I8DYRpzgz3nvx-w-FNQpr-YWck7P58vGEGp-ogWUq0RliqKoq0nZE1fwCxd0Bfc0neQiDgr6xZviow1u16uN3nAnnSmL1SQnkfW284-WoldWCHB541V2L_V8JqunukctGWNz888JM-gRwsDqpiFQG4uc0P1h4ZY9UELcW_h4d1WXR1VEPFZ2JHt1fZLids3wIeClkLr3kKX8eh0QsA0SshF_-lNFLNe0g96B4r56TSDsais14BiQhgb4R_FMDz2rFpjY7Nh37Siiw6VcBTBHBmu3ngXlmCbO0wDwpnK22yFl0PlKa8UZVsm1Fc3_OSDtuwawLYV1azZPFYAMw6JaGjDrfUiSmN2k_GzZ2VdOxHGG01cXc_0jJZ40AUmdasrIEdIy-XwW1_VvUFpgJvBkfGFHMx45HwLtWcAq7RROCt6AM2o342d3vPFR-QFVG_HunRBF3Yit3zl5t2w-43P2dQlIffcINdWyzAxh3GUe3iGTpw03ySnCEJ6FkLPuHPKVWqJxYsjeKkz82nlasKxkte1DUYNOBjN4FwiM3xZjNrAgGvEIxzaNP-JPRo2DqYV4MpBhKcolGhWK4yfty8B5LeQDO5r5C7eCcu5xkbScxxkyg_Ok2WdhH5na7_4g0OjY5RU")
                .retrieve()
                .body(ApiResponse.class);

        log.info(apiResponse.toString());

        return apiResponse.getData().get(0).getId();


    }


    private void postToInfraspeak(String childId, String description, Integer problemId){

        TicketBody ticketBody = new TicketBody(problemId,description,4,192021,Integer.parseInt(childId),0,"contact",0,0,true);

        RestClient restClient = RestClient.create();
        ResponseEntity<Void> response = restClient.post()
                .uri(URI.create("https://api.infraspeak.com/v3/failures"))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxNzkyIiwianRpIjoiYTFjMTA5NWI4MjY3MjZhYWZmYzMzYjA2MjBlMzA5NDI3NDUwMTkwMWE3NDMxYzgyNWMyMzBkOGU2OTRlYWI2NmRkYjUxNGE0NThmMTRlMGQiLCJpYXQiOjE3MTA0MjQyMDUsIm5iZiI6MTcxMDQyNDIwNSwiZXhwIjoyMDI1OTU3MDA1LCJzdWIiOiI5NDQxNCIsInNjb3BlcyI6W10sInV0eXBlIjoiT1BFUkFUT1IiLCJ1dHlwZV9pZCI6ODQ1NTQsIm5hbWUiOiJJbnRlZ3Jhw6fDo28gU2FsZXNmb3JjZSIsImVtYWlsIjoiaW50ZWdyYWNhbytzYWxlc2ZvcmNlQGluZnJhc3BlYWsuY29tIiwiem9uZWluZm8iOiJFdXJvcGUvTGlzYm9uIn0.hfZr5JeFKSDwFt3kfof2wh1TNnCEzmoV3pNd0Ax-I8DYRpzgz3nvx-w-FNQpr-YWck7P58vGEGp-ogWUq0RliqKoq0nZE1fwCxd0Bfc0neQiDgr6xZviow1u16uN3nAnnSmL1SQnkfW284-WoldWCHB541V2L_V8JqunukctGWNz888JM-gRwsDqpiFQG4uc0P1h4ZY9UELcW_h4d1WXR1VEPFZ2JHt1fZLids3wIeClkLr3kKX8eh0QsA0SshF_-lNFLNe0g96B4r56TSDsais14BiQhgb4R_FMDz2rFpjY7Nh37Siiw6VcBTBHBmu3ngXlmCbO0wDwpnK22yFl0PlKa8UZVsm1Fc3_OSDtuwawLYV1azZPFYAMw6JaGjDrfUiSmN2k_GzZ2VdOxHGG01cXc_0jJZ40AUmdasrIEdIy-XwW1_VvUFpgJvBkfGFHMx45HwLtWcAq7RROCt6AM2o342d3vPFR-QFVG_HunRBF3Yit3zl5t2w-43P2dQlIffcINdWyzAxh3GUe3iGTpw03ySnCEJ6FkLPuHPKVWqJxYsjeKkz82nlasKxkte1DUYNOBjN4FwiM3xZjNrAgGvEIxzaNP-JPRo2DqYV4MpBhKcolGhWK4yfty8B5LeQDO5r5C7eCcu5xkbScxxkyg_Ok2WdhH5na7_4g0OjY5RU")
                .body(ticketBody)
                .retrieve()
                .toBodilessEntity();


    }
}
