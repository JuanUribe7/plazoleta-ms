package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public class DishUpdateValidator {

    private DishUpdateValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validateDish(Dish dish) {



        if (dish.getDescription() == null || dish.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (!dish.getDescription().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException("La descripción solo puede contener letras, números, espacios, puntos y comas");
        }
        if (dish.getDescription().length() < 10 || dish.getDescription().length() > 200) {
            throw new IllegalArgumentException("La descripción debe tener entre 10 y 200 caracteres");
        }

        // Validación de precio
        System.out.println("Validando precio: " + dish.getPrice());
        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException("Precio debe ser mayor a 0");
        }
    }
}
