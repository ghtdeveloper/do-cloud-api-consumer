package com.neptune.dto.request.person;

import com.neptune.dto.request.address.CreateAddressDto;
import com.neptune.dto.request.phone.CreatePhoneDto;
import com.neptune.utils.ActiveStatusValid;
import com.neptune.utils.SexValid;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

import static com.neptune.utils.RegexPattern.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePersonDto implements Serializable {

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "firstName is invalid")
    private String firstName;

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "paternalName is invalid")
    private String paternalName;

    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "maternalName is invalid")
    private String maternalName;

    @Pattern(regexp = REGEX_ONLY_LETTERS_AND_SPACE, message = "fullName is invalid")
    private String fullName;

    private Integer age;

    @SexValid
    private char sex;

    @ActiveStatusValid
    private char activeStatus;

    @Valid
    private CreatePhoneDto createPhoneDto;

    @Valid
    private CreateAddressDto createAddressDto;

    @Pattern(regexp = REGEX_ONLY_LETTERS_AND_SPACE, message = "occupation is invalid")
    private String occupation;

    private LocalDate createdOn;
}
