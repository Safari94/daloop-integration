package com.klc.daloopintegration.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketBody {

    private int problemId;
    @JsonProperty("stationCode")
    private String stationCode;


}
