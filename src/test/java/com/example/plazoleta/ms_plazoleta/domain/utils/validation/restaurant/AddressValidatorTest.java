package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<AddressValidator> constructor = AddressValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }

    @Test
    void addressShouldNotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate(null));
        assertEquals("La dirección no puede estar vacía ni ser solo espacios", exception.getMessage());
    }

    @Test
    void addressShouldNotBeEmptyOrSpaces() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("   "));
        assertEquals("La dirección no puede estar vacía ni ser solo espacios", exception.getMessage());
    }

    @Test
    void addressShouldNotContainInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("Calle 45 @$%"));
        assertEquals("La dirección solo puede contener letras, números, espacios, guiones, puntos, comas y numeral", exception.getMessage());
    }

    @Test
    void addressTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("Abc"));
        assertEquals("La dirección debe tener entre 5 y 100 caracteres", exception.getMessage());
    }

    @Test
    void addressTooLong() {
        String longAddress = "A".repeat(101);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate(longAddress));
        assertEquals("La dirección debe tener entre 5 y 100 caracteres", exception.getMessage());
    }

    @Test
    void addressShouldNotBeOnlyNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("12345"));
        assertEquals("La dirección no puede contener solo números", exception.getMessage());
    }

    @Test
    void validAddressShouldPass() {
        assertDoesNotThrow(() -> AddressValidator.validate("Calle 45 #10-20"));
    }
}
