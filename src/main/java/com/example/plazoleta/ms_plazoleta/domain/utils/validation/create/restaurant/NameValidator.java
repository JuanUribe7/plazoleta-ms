package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class NameValidator  {
    private NameValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }


    public static void validate(String name ) {


        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_INVALID_CHARS, "Name", "letters and spaces"));
        }

        if (name.contains("  ")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.NAME_DOUBLE_SPACES);
        }

        if (name.startsWith(" ") || name.endsWith(" ")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.NAME_LEADING_TRAILING_SPACES);
        }

        if (name.length() < 2) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_MIN_LENGTH, "Name", 2));
        }
    }
    }
