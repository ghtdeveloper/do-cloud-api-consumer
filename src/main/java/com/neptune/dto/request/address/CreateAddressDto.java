package com.neptune.dto.request.address;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateAddressDto implements Serializable {
    private String postalCode;
    private LocalDate createdOn;
    private Long countryId;
    private Long personId;
}
