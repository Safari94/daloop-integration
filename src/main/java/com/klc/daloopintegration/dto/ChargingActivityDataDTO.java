package com.klc.daloopintegration.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Data
public class ChargingActivityDataDTO {

    private List<ChargingActivityDTO> data;
    private MetaDTO meta;
}

