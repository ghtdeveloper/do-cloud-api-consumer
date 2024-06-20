package com.neptune.dto.response.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neptune.dto.response.country.CountryResponseDto;
import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class AddressResponseDto {
    private Long id;
    private CountryResponseDto countryResponseDto;
    private String postalCode;
    private String state;
    private String town;
    private LocalDate createdOn;
}
