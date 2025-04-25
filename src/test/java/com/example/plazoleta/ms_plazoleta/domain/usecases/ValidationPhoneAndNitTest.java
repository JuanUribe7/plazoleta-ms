package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.NitValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.PhoneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationPhoneAndNitTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validPhoneShouldPass() {
        assertDoesNotThrow(() -> PhoneValidator.validate("+573001112233"));
    }

    @Test
    void invalidPhoneShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PhoneValidator.validate("++123"));
        assertEquals("El '+' solo puede estar al inicio y una sola vez", exception.getMessage());
    }

    @Test
    void validNitShouldPass() {
        assertDoesNotThrow(() -> NitValidator.validate("12345678", restaurantPersistencePort));
    }

    @Test
    void invalidNitShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> NitValidator.validate("12AB5678", restaurantPersistencePort));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }
}
