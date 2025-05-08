package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant.LogoValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant.NameValidator;



public class DishFieldValidator {

    private DishFieldValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validate(Dish dish) {
        NameValidator.validate(dish.getName());
        DescriptionValidator.validate(dish.getDescription());
        LogoValidator.validate(dish.getImageUrl());
        CategoryValidator.validate(dish.getCategory().name());
        validatePrice(dish.getPrice());
    }

    public static void validatePrice(Number price) {
        if (price == null || price.doubleValue() <= 0) {
            throw new InvalidFieldException(ErrorFieldsMessages.DISH_PRICE_INVALID);
        }
    }
}

