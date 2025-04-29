package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public class DishUpdateValidator {
    private DishUpdateValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validateDish(Dish dish) {
        // validar descripción
        if (dish.getDescription() == null || dish.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_EMPTY);
        }
        if (!dish.getDescription().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_ALLOWED_CHARS);
        }
        if (dish.getDescription().length() < 10 || dish.getDescription().length() > 200) {
            throw new IllegalArgumentException(ValidationMessages.DESCRIPTION_LENGTH);
        }

        // Validación de precio
        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException(ValidationMessages.PRICE_POSITIVE);
        }
    }
}
