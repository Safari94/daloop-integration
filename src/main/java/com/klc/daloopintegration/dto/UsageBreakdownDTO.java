package com.klc.daloopintegration.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Data
public class UsageBreakdownDTO {

    private String businessUnit;
    private String usage;
    private String idFare;
    private String initDate;
    private String endDate;
    private String totalPrice;
    private String totalDuration;
    private String voltageLevel;
    private String region;
    private List<VatDTO> vats;
    private List<DetailDTO> details;
    private String dateCreated;
    private String dateLastUpdate;
    private String id;
}
