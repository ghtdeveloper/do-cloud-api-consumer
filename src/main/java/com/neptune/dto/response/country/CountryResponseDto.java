package com.neptune.dto.response.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neptune.dto.response.address.AddressResponseDto;
import com.neptune.dto.response.phone.PhoneResponseDto;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class CountryResponseDto {
    private Long id;
    private String name;
}
