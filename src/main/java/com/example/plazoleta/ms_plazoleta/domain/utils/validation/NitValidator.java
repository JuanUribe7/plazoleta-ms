package com.example.plazoleta.ms_plazoleta.domain.utils.validation;


import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.InvalidNitException;

public class NitValidator {
    private NitValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(String nit, IRestaurantPersistencePort restaurantPersistencePort) {

        if (restaurantPersistencePort.findByNit(nit).isPresent()){
            throw new InvalidNitException("El nit ya está registrado.");
        }
        if (nit == null || nit.trim().isEmpty()) {
            throw new IllegalArgumentException("El NIT no puede estar vacío");
        }

        if (!nit.matches("^[0-9]{8,15}$")) {
            throw new IllegalArgumentException("El NIT debe contener solo dígitos entre 8 y 15 caracteres");
        }

        if (nit.startsWith("0")) {
            throw new IllegalArgumentException("El NIT no puede comenzar con cero");
        }
    }
}
