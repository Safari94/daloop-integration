package com.klc.daloopintegration.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class ChargingActivityDTO {

    private String type;
    private String id;
    private AttributesDTO attributes;
    private RelationshipsDTO relationships;


}
