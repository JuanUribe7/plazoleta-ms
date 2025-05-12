package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionValidatorTest {

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DescriptionValidator.validate(null));
        assertEquals("La descripción no puede estar vacía", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DescriptionValidator.validate(""));
        assertEquals("La descripción no puede estar vacía", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionHasInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DescriptionValidator.validate("Descripción $$$"));
        assertEquals("La descripción solo puede contener letras, números, espacios, puntos y comas", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DescriptionValidator.validate("Corto."));
        assertEquals("La descripción debe tener entre 10 y 200 caracteres", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionTooLong() {
        String longDescription = "a".repeat(201);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DescriptionValidator.validate(longDescription));
        assertEquals("La descripción debe tener entre 10 y 200 caracteres", exception.getMessage());
    }

    @Test
    void shouldPassValidationForValidDescription() {
        assertDoesNotThrow(() -> DescriptionValidator.validate("Esta es una descripción válida, con puntos y comas."));
    }

    @Test
    void testPrivateConstructorThrowsException() throws Exception {
        Constructor<DescriptionValidator> constructor = DescriptionValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
