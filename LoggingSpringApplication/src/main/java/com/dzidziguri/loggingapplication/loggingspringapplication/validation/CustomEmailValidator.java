package com.dzidziguri.loggingapplication.loggingspringapplication.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String > {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s!=null && s.isBlank()!=true && s.matches("^(.+)@(.+)$") && s.length()<40;
    }
}
