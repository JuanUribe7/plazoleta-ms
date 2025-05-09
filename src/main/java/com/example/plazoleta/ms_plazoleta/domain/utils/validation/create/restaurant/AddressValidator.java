package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;

public class AddressValidator {
    private AddressValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidFieldException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Address"));
        }

        if (!address.matches("^[a-zA-Z0-9\\s\\-.,#]+$")) {
            throw new InvalidFieldException(ExceptionMessages.INVALID_ADDRESS);
        }

        if (address.length() < 5 || address.length() > 100) {
            throw new InvalidFieldException(
                    String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, "Address", 5, 100));
        }

        if (address.matches("^[0-9]+$")) {
            throw new InvalidFieldException(ErrorFieldsMessages.ADDRESS_ONLY_NUMBERS);
        }
    }
}