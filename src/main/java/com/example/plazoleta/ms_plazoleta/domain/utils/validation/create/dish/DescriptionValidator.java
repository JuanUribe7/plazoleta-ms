package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class DescriptionValidator {
    private DescriptionValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Description"));
        }

        if (!description.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.DESCRIPTION_INVALID_CHARS);
        }

        if (description.length() < 10 || description.length() > 200) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, "Description", 10, 200));
        }
    }
}