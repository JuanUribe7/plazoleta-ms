package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;


public class LogoValidator {

    private LogoValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new InvalidFieldException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Logo URL"));
        }

        if (url.contains("localhost") || url.contains("127.0.0.1")) {
            throw new InvalidFieldException(ErrorFieldsMessages.LOGO_LOCAL_URL);
        }

        if (!url.matches("^(http|https)://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$")) {
            throw new InvalidFieldException(ErrorFieldsMessages.LOGO_INVALID_FORMAT);
        }

        if (!url.matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new InvalidFieldException(ErrorFieldsMessages.LOGO_INVALID_EXTENSION);
        }
    }
}
