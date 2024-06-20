package com.neptune.dto.response.address;

import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddressCollectionResponse {
    private List<AddressResponseDto> addressResponseDtoList;
}
