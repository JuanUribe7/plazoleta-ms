package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantCreateValidatorTest {

    @Test
    void shouldValidateSuccessfullyWithValidRestaurant() {
        Restaurant restaurant = new Restaurant(
                1L,
                "ValidName",
                "12345678",
                "Calle 123 #45-67",
                "+573001112233",
                "http://valid.com/logo.png",
                5L
        );

        assertDoesNotThrow(() -> RestaurantCreateValidator.validate(restaurant));
    }

    @Test
    void shouldThrowExceptionForInvalidNit() {
        Restaurant restaurant = new Restaurant(
                1L,
                "ValidName",
                "012345", // Invalid nit
                "Calle 123 #45-67",
                "+573001112233",
                "http://valid.com/logo.png",
                5L
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> RestaurantCreateValidator.validate(restaurant));
        assertEquals("El NIT debe contener solo dígitos entre 8 y 15 caracteres", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidOwnerId() {
        Restaurant restaurant = new Restaurant(
                1L,
                "ValidName",
                "12345678",
                "Calle 123 #45-67",
                "+573001112233",
                "http://valid.com/logo.png",
                -1L // Invalid ownerId
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> RestaurantCreateValidator.validate(restaurant));
        assertEquals("El ID del propietario debe ser un número positivo", exception.getMessage());
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<RestaurantCreateValidator> constructor = RestaurantCreateValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}
