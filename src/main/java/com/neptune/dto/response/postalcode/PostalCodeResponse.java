package com.neptune.dto.response.postalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostalCodeResponse {
    private  boolean error;
    private String  message;
    @JsonProperty("codigo_postal")
    private PostalCodeDetails postalCode;
}
