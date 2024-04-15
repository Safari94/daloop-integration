package com.klc.daloopintegration.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

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
    private List<VatDTO> vats;
    private List<DetailDTO> details;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastUpdate;
    private String id;
}
