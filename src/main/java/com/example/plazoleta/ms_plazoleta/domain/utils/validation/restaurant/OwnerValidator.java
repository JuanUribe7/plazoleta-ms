package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;

public class OwnerValidator {
    private OwnerValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(Long ownerId) {
        if (ownerId == null) {
            throw new IllegalArgumentException(ValidationMessages.OWNER_ID_NULL);
        }
        if (ownerId <= 0) {
            throw new IllegalArgumentException(ValidationMessages.OWNER_ID_POSITIVE);
        }
    }
}