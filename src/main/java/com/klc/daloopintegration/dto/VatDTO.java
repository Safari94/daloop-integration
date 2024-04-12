package com.klc.daloopintegration.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class VatDTO {

    private String amountValue;
    private String dateCreated;
    private String dateLastUpdate;
}
