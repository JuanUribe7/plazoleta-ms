package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ValidationMessages;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionValidatorTest {

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> DescriptionValidator.validate(null)
        );
        assertEquals(
                ValidationMessages.DESCRIPTION_EMPTY,
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> DescriptionValidator.validate("")
        );
        assertEquals(
                ValidationMessages.DESCRIPTION_EMPTY,
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDescriptionHasInvalidCharacters() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> DescriptionValidator.validate("Descripción $$$")
        );
        assertEquals(
                ValidationMessages.DESCRIPTION_ALLOWED_CHARS,
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDescriptionTooShort() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> DescriptionValidator.validate("Corto.")
        );
        assertEquals(
                ValidationMessages.DESCRIPTION_LENGTH,
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDescriptionTooLong() {
        String longDescription = "a".repeat(201);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> DescriptionValidator.validate(longDescription)
        );
        assertEquals(
                ValidationMessages.DESCRIPTION_LENGTH,
                exception.getMessage()
        );
    }

    @Test
    void shouldPassValidationForValidDescription() {
        assertDoesNotThrow(
                () -> DescriptionValidator.validate("Esta es una descripción válida, con puntos y comas.")
        );
    }

    @Test
    void testPrivateConstructorThrowsException() throws Exception {
        Constructor<DescriptionValidator> constructor =
                DescriptionValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );
        assertTrue(
                exception.getCause() instanceof UnsupportedOperationException
        );
        assertEquals(
                ValidationMessages.UTILITY_CLASS,
                exception.getCause().getMessage()
        );
    }
}