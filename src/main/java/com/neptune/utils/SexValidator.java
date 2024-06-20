package com.neptune.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

public class SexValidator implements ConstraintValidator<SexValid, Character> {
    @Override
    public void initialize(@NotNull SexValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Character value, ConstraintValidatorContext constraintValidatorContext) {
        return value.equals('F') || value.equals('M');
    }
}