package com.example.plazoleta.ms_plazoleta.domain.utils.validation;

public class AddressValidator {
    private AddressValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía ni ser solo espacios");
        }

        if (!address.matches("^[a-zA-Z0-9\\s\\-.,#]+$")) {
            throw new IllegalArgumentException("La dirección solo puede contener letras, números, espacios, guiones, puntos, comas y numeral");
        }

        if (address.length() < 5 || address.length() > 100) {
            throw new IllegalArgumentException("La dirección debe tener entre 5 y 100 caracteres");
        }

        if (address.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("La dirección no puede contener solo números");
        }
    }
}
