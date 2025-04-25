package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.NitValidator;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.InvalidNitException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NitValidatorTest {

    @Test
    void existingNitShouldFail() {
        IRestaurantPersistencePort mockPort = mock(IRestaurantPersistencePort.class);
        Restaurant fakeRestaurant = new Restaurant(); // crea un objeto válido si es necesario
        when(mockPort.findByNit("12345678")).thenReturn(Optional.of(fakeRestaurant));

        Exception exception = assertThrows(InvalidNitException.class, () -> NitValidator.validate("12345678", mockPort));
        assertEquals("El nit ya está registrado.", exception.getMessage());
    }

    @Test
    void nullNitShouldFail() {
        IRestaurantPersistencePort mockPort = mock(IRestaurantPersistencePort.class);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate(null, mockPort));
        assertEquals("El NIT no puede estar vacío", exception.getMessage());
    }

    @Test
    void invalidFormatShouldFail() {
        IRestaurantPersistencePort mockPort = mock(IRestaurantPersistencePort.class);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("abc", mockPort));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }

    @Test
    void startsWithZeroShouldFail() {
        IRestaurantPersistencePort mockPort = mock(IRestaurantPersistencePort.class);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("01234567", mockPort));
        assertEquals("El NIT no puede comenzar con cero", exception.getMessage());
    }
    @Test
    void testPrivateConstructorOfNitValidator() throws Exception {
        Constructor<NitValidator> constructor = NitValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
