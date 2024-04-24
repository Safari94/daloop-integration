package com.klc.daloopintegration.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Jacksonized
@Builder
@Data
public class UsageBreakdownDTO {

    private String businessUnit;
    private String usage;
    private String idFare;
    private OffsetDateTime initDate;
    private OffsetDateTime endDate;
    private Double totalPrice;
    private Integer totalDuration;
    private String voltageLevel;
    private String region;
    private Set<VatDTO> vats;
    private Set<DetailDTO> details;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastUpdate;

}
