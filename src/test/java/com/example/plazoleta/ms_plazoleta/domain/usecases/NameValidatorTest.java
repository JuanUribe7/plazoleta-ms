
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.NameValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class NameValidatorTest {

    @Test
    void invalidCharactersShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("12345"));
        assertEquals("El nombre solo puede contener letras y espacios", exception.getMessage());
    }

    @Test
    void doubleSpacesShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("Don  Pepe"));
        assertEquals("El nombre no puede tener espacios dobles", exception.getMessage());
    }

    @Test
    void startOrEndWithSpaceShouldFail() {
        Exception start = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate(" Don"));
        assertEquals("El nombre no puede comenzar ni terminar con espacio", start.getMessage());

        Exception end = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("Don "));
        assertEquals("El nombre no puede comenzar ni terminar con espacio", end.getMessage());
    }

    @Test
    void tooShortShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NameValidator.validate("D"));
        assertEquals("El nombre debe tener al menos 2 caracteres", exception.getMessage());
    }

    @Test
    void testPrivateConstructorOfNameValidator() throws Exception {
        Constructor<NameValidator> constructor = NameValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
