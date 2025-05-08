package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class PhoneValidator{

    private PhoneValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validate(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Phone number"));
        }

        if (phone.indexOf('+') > 0 || phone.chars().filter(ch -> ch == '+').count() > 1) {
            throw new IllegalArgumentException(ErrorFieldsMessages.PHONE_PLUS_POSITION);
        }

        String numberOnly = phone.startsWith("+") ? phone.substring(1) : phone;

        if (numberOnly.startsWith("00")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.PHONE_DOUBLE_ZERO);
        }

        if (!numberOnly.matches("^[0-9]{10,13}$")) {
            throw new IllegalArgumentException(ErrorFieldsMessages.PHONE_INVALID_FORMAT);
        }
    }


}
