package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant.PhoneValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    @Test
    void shouldThrowExceptionWhenPhoneIsNullOrEmpty() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate(null));
        assertEquals("El número de teléfono no puede estar vacío", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate(" "));
        assertEquals("El número de teléfono no puede estar vacío", ex2.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlusIsNotAtStartOrMultiple() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("123+4567890"));
        assertEquals("El '+' solo puede estar al inicio y una sola vez", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+123+4567890"));
        assertEquals("El '+' solo puede estar al inicio y una sola vez", ex2.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStartsWithDoubleZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+001234567890"));
        assertEquals("El número no puede comenzar con '00'", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInvalidLengthOrCharacters() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+12345"));
        assertEquals("El número debe tener entre 10 y 13 caracteres y contener solo dígitos, con un '+' opcional al inicio", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+12345678901234"));
        assertEquals("El número debe tener entre 10 y 13 caracteres y contener solo dígitos, con un '+' opcional al inicio", ex2.getMessage());

        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("+12345abcde"));
        assertEquals("El número debe tener entre 10 y 13 caracteres y contener solo dígitos, con un '+' opcional al inicio", ex3.getMessage());
    }

    @Test
    void shouldPassWithValidPhoneNumbers() {
        assertDoesNotThrow(() -> PhoneValidator.validate("+573001112233"));
        assertDoesNotThrow(() -> PhoneValidator.validate("573001112233"));
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<PhoneValidator> constructor = PhoneValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
