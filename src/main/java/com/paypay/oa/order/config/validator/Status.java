package com.paypay.oa.order.config.validator;

import com.paypay.oa.order.config.validator.impl.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StatusValidator.class})
public @interface Status {
    String message() default "Please provide correct value for OrderStatus";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}