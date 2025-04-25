package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.OwnerValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerValidatorTest {

    @Test
    void nullOwnerIdShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OwnerValidator.validate(null));
        assertEquals("El ID del propietario no puede ser nulo", exception.getMessage());
    }

    @Test
    void negativeOwnerIdShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OwnerValidator.validate(-1L));
        assertEquals("El ID del propietario debe ser un número positivo", exception.getMessage());
    }
    @Test
    void testPrivateConstructorOfOwnerValidator() throws Exception {
        Constructor<OwnerValidator> constructor = OwnerValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}