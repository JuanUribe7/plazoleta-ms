package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class CategoryValidator {
    private CategoryValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.CATEGORY_EMPTY);
        }
        if (!category.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException(ValidationMessages.CATEGORY_ONLY_LETTERS);
        }
        if (category.length() < 3 || category.length() > 30) {
            throw new IllegalArgumentException(ValidationMessages.CATEGORY_LENGTH);
        }
    }
}