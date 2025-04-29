package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class DescriptionValidator {
    private DescriptionValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_EMPTY);
        }
        if (!description.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_ALLOWED_CHARS);
        }
        if (description.length() < 10 || description.length() > 200) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_LENGTH);
        }
    }
}
