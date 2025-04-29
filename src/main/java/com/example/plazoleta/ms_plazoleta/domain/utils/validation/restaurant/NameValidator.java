package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class NameValidator {
    private NameValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String name) {
        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            throw new IllegalArgumentException(ValidationMessages.NAME_ONLY_LETTERS);
        }
        if (name.contains("  ")) {
            throw new IllegalArgumentException(ValidationMessages.NAME_NO_DOUBLE_SPACES);
        }
        if (name.startsWith(" ") || name.endsWith(" ")) {
            throw new IllegalArgumentException(ValidationMessages.NAME_NO_TRIM_SPACES);
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException(ValidationMessages.NAME_MIN_LENGTH);
        }
    }
}