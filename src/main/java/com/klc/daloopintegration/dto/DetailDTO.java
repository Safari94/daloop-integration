package com.klc.daloopintegration.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Builder
@Data
public class DetailDTO {

    private String component;
    private Double amountValue;
    private String unit;
    private boolean visible;
    private boolean included;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastUpdate;
}
