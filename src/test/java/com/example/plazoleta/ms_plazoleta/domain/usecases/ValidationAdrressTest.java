package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.AddressValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationAdrressTest {
    @Test
    void nullAddressShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate(null));
        assertEquals("La dirección no puede estar vacía ni ser solo espacios", exception.getMessage());
    }

    @Test
    void invalidCharactersShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("$$ Calle ?!"));
        assertEquals("La dirección solo puede contener letras, números, espacios, guiones, puntos, comas y numeral", exception.getMessage());
    }

    @Test
    void tooShortOrTooLongShouldFail() {
        Exception exceptionShort = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("1234"));
        assertEquals("La dirección debe tener entre 5 y 100 caracteres", exceptionShort.getMessage());

        String longAddress = "A".repeat(101);
        Exception exceptionLong = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate(longAddress));
        assertEquals("La dirección debe tener entre 5 y 100 caracteres", exceptionLong.getMessage());
    }

    @Test
    void onlyNumbersShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AddressValidator.validate("123456"));
        assertEquals("La dirección no puede contener solo números", exception.getMessage());
    }

    @Test
    void testPrivateConstructorOfAddressValidator() throws Exception {
        Constructor<AddressValidator> constructor = AddressValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
