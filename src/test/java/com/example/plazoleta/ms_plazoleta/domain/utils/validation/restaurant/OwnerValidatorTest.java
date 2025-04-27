package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class OwnerValidatorTest {

    @Test
    void shouldThrowExceptionWhenOwnerIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> OwnerValidator.validate(null));
        assertEquals("El ID del propietario no puede ser nulo", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOwnerIdIsZeroOrNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> OwnerValidator.validate(0L));
        assertEquals("El ID del propietario debe ser un número positivo", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> OwnerValidator.validate(-1L));
        assertEquals("El ID del propietario debe ser un número positivo", exception.getMessage());
    }

    @Test
    void shouldPassWhenOwnerIdIsPositive() {
        assertDoesNotThrow(() -> OwnerValidator.validate(5L));
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<OwnerValidator> constructor = OwnerValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
