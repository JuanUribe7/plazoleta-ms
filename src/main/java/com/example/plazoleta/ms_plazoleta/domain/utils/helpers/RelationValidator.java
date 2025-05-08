package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class RelationValidator {

    private RelationValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new SecurityException(errorMessage);
        }
    }
}
