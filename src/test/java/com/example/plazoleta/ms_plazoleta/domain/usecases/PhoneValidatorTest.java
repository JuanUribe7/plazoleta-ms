
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.PhoneValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

    @Test
    void nullPhoneShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate(null));
        assertEquals("El número de teléfono no puede estar vacío", exception.getMessage());
    }

    @Test
    void multiplePlusShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("++573001112233"));
        assertEquals("El '+' solo puede estar al inicio y una sola vez", exception.getMessage());
    }

    @Test
    void startsWithDoubleZeroShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+00573001112233"));
        assertEquals("El número no puede comenzar con '00'", exception.getMessage());
    }

    @Test
    void invalidLengthOrCharactersShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+573abc112233"));
        assertEquals("El número debe tener entre 10 y 13 caracteres y contener solo dígitos, con un '+' opcional al inicio", exception.getMessage());
    }

    @Test
    void validPhoneShouldPass() {
        assertDoesNotThrow(() -> PhoneValidator.validate("+573001112233"));
    }

    @Test
    void testPrivateConstructorOfPhoneValidator() throws Exception {
        Constructor<PhoneValidator> constructor = PhoneValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
