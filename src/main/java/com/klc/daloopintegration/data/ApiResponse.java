package com.klc.daloopintegration.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {

    @JsonProperty("data")
    private List<Response> data;
    private Meta meta;
    private Links links;
}
