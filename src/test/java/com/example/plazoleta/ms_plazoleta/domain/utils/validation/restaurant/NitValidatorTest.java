package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class NitValidatorTest {

    @Test
    void validNitShouldPass() {
        assertDoesNotThrow(() -> NitValidator.validate("12345678"));
    }

    @Test
    void nullNitShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate(null));
        assertEquals("El NIT no puede estar vacío", exception.getMessage());
    }

    @Test
    void emptyNitShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("   "));
        assertEquals("El NIT no puede estar vacío", exception.getMessage());
    }

    @Test
    void nitWithLettersShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("12345abc"));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }

    @Test
    void nitTooShortShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("1234567"));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }

    @Test
    void nitTooLongShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("1234567890123456"));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }

    @Test
    void nitStartingWithZeroShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("01234567"));
        assertEquals("El NIT no puede comenzar con cero", exception.getMessage());
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        var constructor = NitValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }

}
