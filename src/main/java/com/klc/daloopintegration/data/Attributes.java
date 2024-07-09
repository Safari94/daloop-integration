package com.klc.daloopintegration.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attributes {

    @JsonProperty("local_id")
    private int localId;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String code;
    @JsonProperty("full_code")
    private String fullCode;
    private String observation;
    @JsonProperty("entity_id")
    private int entityId;
    @JsonProperty("date_updated")
    private String dateUpdated;
    @JsonProperty("date_deleted")
    private String dateDeleted;
    @JsonProperty("is_from_network")
    private boolean isFromNetwork;
    @JsonProperty("local_uuid")
    private String localUuid;
    @JsonProperty("has_children")
    private boolean hasChildren;
    @JsonProperty("client_id")
    private int clientId;
    @JsonProperty("has_network_mapping")
    private boolean hasNetworkMapping;
}
