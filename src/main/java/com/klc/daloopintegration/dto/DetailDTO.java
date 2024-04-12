package com.klc.daloopintegration.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class DetailDTO {

    private String component;
    private String amountValue;
    private String unit;
    private boolean visible;
    private boolean included;
    private String dateCreated;
    private String dateLastUpdate;
}
