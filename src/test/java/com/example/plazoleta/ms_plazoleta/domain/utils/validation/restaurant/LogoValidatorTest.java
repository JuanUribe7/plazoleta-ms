package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoValidatorTest {

    @Test
    void shouldThrowIfUrlIsNull() {
        InvalidFieldException ex = assertThrows(InvalidFieldException.class, () -> LogoValidator.validate(null));
        assertEquals(String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Logo URL"), ex.getMessage());
    }

    @Test
    void shouldThrowIfUrlIsBlank() {
        InvalidFieldException ex = assertThrows(InvalidFieldException.class, () -> LogoValidator.validate("  "));
        assertEquals(String.format(ErrorFieldsMessages.FIELD_REQUIRED, "Logo URL"), ex.getMessage());
    }

    @Test
    void shouldThrowIfUrlIsLocalhost() {
        InvalidFieldException ex = assertThrows(InvalidFieldException.class,
                () -> LogoValidator.validate("http://localhost/image.png"));
        assertEquals(ErrorFieldsMessages.LOGO_LOCAL_URL, ex.getMessage());
    }

    @Test
    void shouldThrowIfUrlHasInvalidFormat() {
        InvalidFieldException ex = assertThrows(InvalidFieldException.class,
                () -> LogoValidator.validate("notaurl"));
        assertEquals(ErrorFieldsMessages.LOGO_INVALID_FORMAT, ex.getMessage());
    }

    @Test
    void shouldThrowIfUrlHasInvalidExtension() {
        InvalidFieldException ex = assertThrows(InvalidFieldException.class,
                () -> LogoValidator.validate("http://example.com/image.txt"));
        assertEquals(ErrorFieldsMessages.LOGO_INVALID_EXTENSION, ex.getMessage());
    }

    @Test
    void shouldPassForValidUrl() {
        assertDoesNotThrow(() -> LogoValidator.validate("https://cdn.example.com/image.png"));
        assertDoesNotThrow(() -> LogoValidator.validate("http://img.example.org/logo.jpeg"));
    }
}
