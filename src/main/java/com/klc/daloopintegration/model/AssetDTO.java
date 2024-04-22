package com.klc.daloopintegration.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class AssetDTO {
    private DataDTO data;
}
