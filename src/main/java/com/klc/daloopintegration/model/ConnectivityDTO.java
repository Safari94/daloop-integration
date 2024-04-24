package com.klc.daloopintegration.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Jacksonized
@Builder
@Data
public class ConnectivityDTO {

    private String eventType;

    @CreationTimestamp
    private LocalDateTime createdDate;


    private String stationInfo;

    private String connectivity;
}
