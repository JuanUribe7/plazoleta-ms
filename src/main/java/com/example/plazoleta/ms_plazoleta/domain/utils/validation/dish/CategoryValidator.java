package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalCategoryException;

public  class CategoryValidator {

    private CategoryValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static CategoryType validate(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalCategoryException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Category"));
        }
        try {
            return CategoryType.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalCategoryException(ErrorFieldsMessages.INVALID_CATEGORY);
        }
    }
}