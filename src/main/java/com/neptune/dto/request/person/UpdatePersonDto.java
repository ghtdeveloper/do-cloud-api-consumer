package com.neptune.dto.request.person;

import com.neptune.utils.ActiveStatusValid;
import lombok.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePersonDto  implements Serializable {
    private Long id;
    @ActiveStatusValid
    private char activeStatus;
}
