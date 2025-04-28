package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.NameValidator;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;

public class DishValidator {


    private DishValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validateDish(Dish dish) {

        NameValidator.validate(dish.getName());

        if (dish.getImageUrl() == null || dish.getImageUrl().trim().isEmpty()) {
            throw new IllegalLogoException("La URL del logo no puede estar vacía");
        }
        if (dish.getImageUrl().contains("localhost") || dish.getImageUrl().contains("127.0.0.1")) {
            throw new IllegalArgumentException("La URL del logo no puede ser local");
        }
        if (!dish.getImageUrl().matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException("La URL del logo debe ser válida y comenzar con http o https");
        }
        if (!dish.getImageUrl().matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new IllegalArgumentException("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg");
        }

        CategoryValidator.validate(dish.getCategory());


        DescriptionValidator.validate(dish.getDescription());



        System.out.println("Validando precio: " + dish.getPrice());
        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException("Precio debe ser mayor a 0");
        }
    }
}
