package com.neptune.dto.response.person;

import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PersonCollectionResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private List<PersonResponseDto> personResponseDtoList;
}
