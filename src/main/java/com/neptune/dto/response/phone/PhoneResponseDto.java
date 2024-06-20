package com.neptune.dto.response.phone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class PhoneResponseDto {
    private Long id;
    private PhoneTypeResponseDto phoneTypeResponseDtos;
    private String phoneNumber;
    private LocalDate createdOn;
}
