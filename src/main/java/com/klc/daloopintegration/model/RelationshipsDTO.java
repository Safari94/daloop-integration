package com.klc.daloopintegration.model;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class RelationshipsDTO {

    private AssetDTO asset;
    private UserDTO user;
    private BusinessUnitDTO businessUnit;


}
