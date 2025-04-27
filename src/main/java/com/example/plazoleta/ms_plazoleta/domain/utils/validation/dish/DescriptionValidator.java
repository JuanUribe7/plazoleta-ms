package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

public class DescriptionValidator {

    private DescriptionValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }

        if (!description.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException("La descripción solo puede contener letras, números, espacios, puntos y comas");
        }

        if (description.length() < 10 || description.length() > 200) {
            throw new IllegalArgumentException("La descripción debe tener entre 10 y 200 caracteres");
        }
    }
}
