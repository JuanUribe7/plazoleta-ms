package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;


import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;

public class FieldValidator {

    private FieldValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validateNotBlank(String field, String errorMessage) {
        if (field == null || field.isBlank())
            throw new InvalidFieldException(errorMessage) ;

        }


    public static void validatePattern(String field, String regex, String errorMessage) {
        if (!field.matches(regex)) {
            throw new InvalidFieldException(errorMessage);
        }
    }

    public static void validateMaxLength(String field, int maxLength, String errorMessage) {
        if (field.length() > maxLength) {
            throw new InvalidFieldException(errorMessage);

        }
    }

    public static void validateMinLength(String field, int minLength, String errorMessage) {
        if (field.length() < minLength) {
            throw new InvalidFieldException(errorMessage);
        }
    }
}