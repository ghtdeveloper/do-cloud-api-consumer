package com.neptune.dto.response.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neptune.dto.response.address.AddressResponseDto;
import com.neptune.dto.response.phone.PhoneResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Setter
@Getter
@ToString
public class PersonResponseDto {
    private Long id;
    private String firstName;
    private String paternalName;
    private String maternalName;
    private String fullName;
    private Integer age;
    private Character sex;
    private Character activeStatus;
    private String occupation;
    private List<AddressResponseDto> addressResponseDto;
    private List<PhoneResponseDto> phoneResponseDto;
    private LocalDate createdOn;
    private String numberToWords;

}
