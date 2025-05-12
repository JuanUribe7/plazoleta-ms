package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.AlreadyExistsException;

public class UniquenessValidator {

    UniquenessValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(ExistenceCheck existenceCheck, String fieldName, String value) {
        if (existenceCheck.exists()) {
            throw new AlreadyExistsException(ExceptionMessages.VALUE_ALREADY_EXISTS + fieldName + ": " + value);
        }
    }

    @FunctionalInterface
    public interface ExistenceCheck {
        boolean exists();
    }
}