package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

public class NitValidator {
    private NitValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String nit, RestaurantPersistencePort port) {
        if (nit == null || nit.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "NIT"));
        }

        if (!nit.matches("^\\d{9,12}$")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.NIT_INVALID_FORMAT);
        }

        if (port.findByNit(nit).isPresent()) {
            throw new IllegalArgumentException(ExceptionMessages.NIT_ALREADY_EXISTS);
        }
    }
}