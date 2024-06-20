package com.neptune.dto.response.postalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;
@Getter
public class PostalCodeDetails {
    @JsonProperty("estado")
    private String state;
    @JsonProperty("estado_abreviatura")
    private String stateAbbreviation;
    @JsonProperty("municipio")
    private String town;
    @JsonProperty("centro_reparto")
    private String centerDistribution;
    @JsonProperty("codigo_postal")
    private String postalCode;
    @JsonProperty("colonias")
    private List<String> colonies;
}
