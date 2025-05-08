package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class AddressValidator {
    private AddressValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Address"));
        }

        if (!address.matches("^[a-zA-Z0-9\\s\\-.,#]+$")) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_INVALID_CHARS,
                            "Address", "letters, numbers, spaces, hyphens, periods, commas and hash"));
        }

        if (address.length() < 5 || address.length() > 100) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, "Address", 5, 100));
        }

        if (address.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.ADDRESS_ONLY_NUMBERS);
        }
    }
}