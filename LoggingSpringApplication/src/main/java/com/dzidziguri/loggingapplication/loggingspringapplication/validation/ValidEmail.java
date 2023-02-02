package com.dzidziguri.loggingapplication.loggingspringapplication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = CustomEmailValidator.class)
public @interface ValidEmail {
    String message() default "Email does not have valid pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
