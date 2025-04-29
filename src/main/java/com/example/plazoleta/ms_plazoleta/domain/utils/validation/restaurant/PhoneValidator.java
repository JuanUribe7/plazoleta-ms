package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;


public class PhoneValidator {
    private PhoneValidator() {
        throw new UnsupportedOperationException(ValidationMessages.UTILITY_CLASS);
    }

    public static void validate(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(ValidationMessages.PHONE_EMPTY);
        }
        if (phone.indexOf('+') > 0 || phone.chars().filter(ch -> ch == '+').count() > 1) {
            throw new IllegalArgumentException(ValidationMessages.PHONE_PLUS_ONLY_AT_START);
        }
        String numberOnly = phone.startsWith("+") ? phone.substring(1) : phone;
        if (numberOnly.startsWith("00")) {
            throw new IllegalArgumentException(ValidationMessages.PHONE_NO_DOUBLE_ZERO);
        }
        if (!numberOnly.matches("^[0-9]{10,13}$")) {
            throw new IllegalArgumentException(ValidationMessages.PHONE_LENGTH_DIGITS);
        }
    }
}
