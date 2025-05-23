package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.NotFoundException;

import java.util.Optional;

public class ExistenceValidator {

    private ExistenceValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static <T> T getIfPresent(Optional<T> optional, String errorMessage) {
        return optional.orElseThrow(() -> new NotFoundException(errorMessage));
    }
}
