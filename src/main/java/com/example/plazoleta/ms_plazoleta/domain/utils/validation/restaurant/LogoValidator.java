package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;

public class LogoValidator {
    private LogoValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalLogoException(ValidationMessages.LOGO_URL_EMPTY);
        }
        if (url.contains("localhost") || url.contains("127.0.0.1")) {
            throw new IllegalArgumentException(ValidationMessages.LOGO_URL_LOCAL);
        }
        if (!url.matches("^(http|https)://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$")) {
            throw new IllegalArgumentException(ValidationMessages.LOGO_URL_VALID);
        }
        if (!url.matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new IllegalArgumentException(ValidationMessages.LOGO_URL_IMAGE_EXT);
        }
    }
}