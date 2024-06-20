package com.neptune.dto.response.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExceptionResponse {
    private String code;
    private String message;
}