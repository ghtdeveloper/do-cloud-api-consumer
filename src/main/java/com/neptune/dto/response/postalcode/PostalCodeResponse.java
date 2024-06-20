package com.neptune.dto.response.postalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
public class PostalCodeResponse {
    private  boolean error;
    private String  message;
    @JsonProperty("codigo_postal")
    private PostalCodeDetails postalCode;
}
