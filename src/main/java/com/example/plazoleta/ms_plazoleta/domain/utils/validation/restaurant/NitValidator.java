package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class NitValidator {
    private NitValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String nit) {
        if (nit == null || nit.trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.NIT_EMPTY);
        }
        if (!nit.matches("^[0-9]{8,15}$")) {
            throw new IllegalArgumentException(ValidationMessages.NIT_DIGITS_8_15);
        }
        if (nit.startsWith("0")) {
            throw new IllegalArgumentException(ValidationMessages.NIT_NO_LEADING_ZERO);
        }
    }
}