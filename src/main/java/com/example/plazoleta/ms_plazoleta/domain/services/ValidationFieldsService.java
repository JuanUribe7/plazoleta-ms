package com.example.plazoleta.ms_plazoleta.domain.services;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.FieldNames;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.FieldValidator;

public class ValidationFieldsService {
    public void validateAddress(String address) {
        FieldValidator.validateNotBlank(address, String.format(ErrorFieldsMessages.FIELD_REQUIRED, FieldNames.ADDRESS));
        FieldValidator.validatePattern(address, "^[a-zA-Z0-9\\s\\-.,#]+$", ExceptionMessages.INVALID_ADDRESS);
        FieldValidator.validateMinLength(address, 5, String.format(ErrorFieldsMessages.FIELD_MIN_LENGTH, FieldNames.ADDRESS, 5));
        FieldValidator.validateMaxLength(address, 100, String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, FieldNames.ADDRESS, 5, 100));
        FieldValidator.validatePattern(address, "^(?!^[0-9]+$).*", ErrorFieldsMessages.ADDRESS_ONLY_NUMBERS);
    }

    public void validateLogo(String url) {
        FieldValidator.validateNotBlank(url, String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Logo URL"));
        FieldValidator.validatePattern(url, "^(?!.*(localhost|127\\.0\\.0\\.1)).*$", ErrorFieldsMessages.LOGO_LOCAL_URL);
        FieldValidator.validatePattern(url, "^(http|https)://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$", ErrorFieldsMessages.LOGO_INVALID_FORMAT);
        FieldValidator.validatePattern(url, ".*\\.(png|jpg|jpeg|svg)$", ErrorFieldsMessages.LOGO_INVALID_EXTENSION);
    }

    public void validateName(String name) {
        FieldValidator.validateNotBlank(name, String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Name"));
        FieldValidator.validatePattern(name, "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", String.format(ErrorFieldsMessages.FIELD_INVALID_CHARS,FieldNames.NAME, "letters and spaces"));
        FieldValidator.validatePattern(name, "^(?!.*  ).*$", ErrorFieldsMessages.NAME_DOUBLE_SPACES);
        FieldValidator.validatePattern(name, "^(?!^ | $).*$", ErrorFieldsMessages.NAME_LEADING_TRAILING_SPACES);
        FieldValidator.validateMinLength(name, 2, String.format(ErrorFieldsMessages.FIELD_MIN_LENGTH, FieldNames.NAME, 2));
    }

    public void validateNit(String nit) {
        FieldValidator.validateNotBlank(nit, String.format(ErrorFieldsMessages.FIELD_REQUIRED, "NIT"));
        FieldValidator.validatePattern(nit, "^\\d{9,12}$", ErrorFieldsMessages.NIT_INVALID_FORMAT);

    }

    public void validatePhone(String phone) {
        FieldValidator.validateNotBlank(phone, String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Phone number"));
        FieldValidator.validatePattern(phone, "^(?!.*\\+.*\\+).*$", ErrorFieldsMessages.PHONE_PLUS_POSITION);
        FieldValidator.validatePattern(phone, "^(?!.*\\+.*00).*$", ErrorFieldsMessages.PHONE_DOUBLE_ZERO);
        FieldValidator.validatePattern(phone, "^[+]?\\d{10,13}$", ErrorFieldsMessages.PHONE_INVALID_FORMAT);
    }

    public  void validateDescription(String description) {
        FieldValidator.validateNotBlank(description, String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Description"));
        FieldValidator.validatePattern(description, "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$", ErrorFieldsMessages.DESCRIPTION_INVALID_CHARS);
        FieldValidator.validateMinLength(description, 10, String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, "Description", 10, 200));
        FieldValidator.validateMaxLength(description, 200, String.format(ErrorFieldsMessages.FIELD_LENGTH_RANGE, "Description", 10, 200));
    }


}
