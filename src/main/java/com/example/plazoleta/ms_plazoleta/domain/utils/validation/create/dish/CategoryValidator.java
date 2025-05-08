package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;


public  class CategoryValidator {

    private CategoryValidator() {
        throw new UnsupportedOperationException(ErrorFieldsMessages.DISH_CATEGORY_REQUIRED);
    }

    public static CategoryType validate(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new InvalidFieldException(
                    String.format(ErrorFieldsMessages.DISH_CATEGORY_REQUIRED));
        }
        try {
            return CategoryType.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldException(ErrorFieldsMessages.INVALID_CATEGORY);
        }
    }
}