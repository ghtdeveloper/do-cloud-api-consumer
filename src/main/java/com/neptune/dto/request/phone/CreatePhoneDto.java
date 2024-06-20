package com.neptune.dto.request.phone;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePhoneDto  implements Serializable {
    private Long phoneTypeId;
    private String phoneNumber;
    private Long personId;
    private LocalDate createdOn;
}
