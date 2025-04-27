package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

public class CategoryValidator {

    private CategoryValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }

        if (!category.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException("La categoría solo puede contener letras y espacios");
        }

        if (category.length() < 3 || category.length() > 30) {
            throw new IllegalArgumentException("La categoría debe tener entre 3 y 30 caracteres");
        }
    }
}
