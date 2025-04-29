package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class AddressValidator {
    private AddressValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.ADDRESS_EMPTY);
        }
        if (!address.matches("^[a-zA-Z0-9\\s\\-.,#]+$")) {
            throw new IllegalArgumentException(ValidationMessages.ADDRESS_ALLOWED_CHARS);
        }
        if (address.length() < 5 || address.length() > 100) {
            throw new IllegalArgumentException(ValidationMessages.ADDRESS_LENGTH);
        }
        if (address.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(ValidationMessages.ADDRESS_NO_ONLY_NUM);
        }
    }
}