package com.example.plazoleta.ms_plazoleta.domain.utils.validation.order;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;


public  class StatusValidator {

    private StatusValidator() {
        throw new UnsupportedOperationException(ErrorFieldsMessages.DISH_CATEGORY_REQUIRED);
    }

    public static OrderStatus validate(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidFieldException(
                    String.format(ErrorFieldsMessages.STATUS_ORDER_REQUIRED));
        }
        try {
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldException(ErrorFieldsMessages.STATUS_ORDER_REQUIRED);
        }
    }
}