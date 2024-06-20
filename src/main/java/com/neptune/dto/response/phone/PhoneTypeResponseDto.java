package com.neptune.dto.response.phone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class PhoneTypeResponseDto {
    private Long id;
    private String typeName;
}
