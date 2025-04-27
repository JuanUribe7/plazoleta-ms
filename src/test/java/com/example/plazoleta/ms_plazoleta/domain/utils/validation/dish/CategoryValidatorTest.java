package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryValidatorTest {

    @Test
    void shouldThrowExceptionWhenCategoryIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CategoryValidator.validate(null));
        assertEquals("La categoría no puede estar vacía", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CategoryValidator.validate(""));
        assertEquals("La categoría no puede estar vacía", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryHasInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CategoryValidator.validate("1234"));
        assertEquals("La categoría solo puede contener letras y espacios", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CategoryValidator.validate("ab"));
        assertEquals("La categoría debe tener entre 3 y 30 caracteres", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryTooLong() {
        String longCategory = "a".repeat(31);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CategoryValidator.validate(longCategory));
        assertEquals("La categoría debe tener entre 3 y 30 caracteres", exception.getMessage());
    }

    @Test
    void shouldPassValidationForValidCategory() {
        assertDoesNotThrow(() -> CategoryValidator.validate("Comida Rápida"));
    }
    @Test
    void testPrivateConstructorThrowsException() throws Exception {
        Constructor<CategoryValidator> constructor = CategoryValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}