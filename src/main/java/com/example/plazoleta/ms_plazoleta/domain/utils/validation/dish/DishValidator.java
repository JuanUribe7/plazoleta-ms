package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.NameValidator;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;


public class DishValidator {
    private DishValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validateDish(Dish dish) {
        NameValidator.validate(dish.getName());

        // validar imagen
        String url = dish.getImageUrl();
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalLogoException(ValidationMessages.IMAGE_URL_EMPTY);
        }
        if (url.contains("localhost") || url.contains("127.0.0.1")) {
            throw new IllegalArgumentException(ValidationMessages.IMAGE_URL_LOCAL);
        }
        if (!url.matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException(ValidationMessages.IMAGE_URL_VALID);
        }
        if (!url.matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new IllegalArgumentException(ValidationMessages.IMAGE_URL_IMAGE_EXT);
        }

        // categorí­a y descripción
        CategoryValidator.validate(dish.getCategory());
        DescriptionValidator.validate(dish.getDescription());

        // precio
        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException(ValidationMessages.PRICE_POSITIVE);
        }
    }
}