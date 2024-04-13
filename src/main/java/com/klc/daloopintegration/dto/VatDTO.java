package com.klc.daloopintegration.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Builder
@Data
public class VatDTO {

    private Double percentageValue;

    private Double amountValue;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastUpdate;
}
