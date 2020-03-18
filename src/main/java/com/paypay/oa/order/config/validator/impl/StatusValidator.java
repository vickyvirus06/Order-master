package com.paypay.oa.order.config.validator.impl;

import com.paypay.oa.order.config.validator.Status;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<Status, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        OrderStatus[] allStatuses = OrderStatus.values();
        for (OrderStatus status : allStatuses) {
            if (status.toString().equals(value)) {
                return true;
            }
        }
        return false;
    }

}