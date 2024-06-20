package com.neptune.dto.response.phone;

import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PhoneCollectionResponseDto {
    private List<PhoneResponseDto> phoneResponseDtos;
}
