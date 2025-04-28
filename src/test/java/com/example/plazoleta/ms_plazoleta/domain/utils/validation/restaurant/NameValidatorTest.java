package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {



    @Test
    void shouldThrowExceptionWhenNameContainsInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("John123"));
        assertEquals("El nombre solo puede contener letras y espacios", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameContainsDoubleSpaces() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("John  Doe"));
        assertEquals("El nombre no puede tener espacios dobles", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameStartsWithSpace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate(" John"));
        assertEquals("El nombre no puede comenzar ni terminar con espacio", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameEndsWithSpace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("John "));
        assertEquals("El nombre no puede comenzar ni terminar con espacio", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("J"));
        assertEquals("El nombre debe tener al menos 2 caracteres", exception.getMessage());
    }

    @Test
    void shouldPassWhenNameIsValid() {
        assertDoesNotThrow(() -> NameValidator.validate("Juan Pérez"));
    }

    @Test
    void shouldNotAllowInstantiationViaReflection() throws Exception {
        Constructor<NameValidator> constructor = NameValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
