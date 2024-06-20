package com.neptune.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SexValidator.class)
public @interface SexValid {
   String message() default Constants.SEX_VALUE_OF_ENUM;
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default  {};
}