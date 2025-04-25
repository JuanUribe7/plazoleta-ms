package com.example.plazoleta.ms_plazoleta.domain.utils.validation;

public class OwnerValidator {
    private OwnerValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(Long ownerId) {
        if (ownerId == null) {
            throw new IllegalArgumentException("El ID del propietario no puede ser nulo");
        }
        if (ownerId <= 0) {
            throw new IllegalArgumentException("El ID del propietario debe ser un número positivo");
        }



    }

   }
