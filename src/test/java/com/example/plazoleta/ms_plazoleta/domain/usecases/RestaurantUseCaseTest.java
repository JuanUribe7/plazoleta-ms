
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private UserFeignClient userFeignClient;
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userFeignClient = mock(UserFeignClient.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userFeignClient);
    }

    @Test
    void createRestaurant_Success() {
        Restaurant restaurant = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        when(userFeignClient.getRoleByUser(1L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("123")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findByName("Rest")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.saveRestaurant(restaurant)).thenReturn(restaurant);

        Restaurant result = restaurantUseCase.createRestaurant(restaurant);

        assertNotNull(result);
        verify(restaurantPersistencePort).saveRestaurant(restaurant);
    }

    @Test
    void createRestaurant_InvalidRole() {
        Restaurant restaurant = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        when(userFeignClient.getRoleByUser(1L)).thenReturn("ADMIN");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(restaurant));
        assertEquals("El id no corresponde a un usuario propietario", exception.getMessage());
    }

    @Test
    void createRestaurant_NitExists() {
        Restaurant restaurant = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        when(userFeignClient.getRoleByUser(1L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("12345678")).thenReturn(Optional.of(restaurant));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(restaurant));
        assertEquals("El NIT del restaurante ya está registrado.", exception.getMessage());
    }

    @Test
    void createRestaurant_NameExists() {
        Restaurant restaurant = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        when(userFeignClient.getRoleByUser(1L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("123")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findByName("Rest")).thenReturn(Optional.of(restaurant));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(restaurant));
        assertEquals("El nombre del restaurante ya está registrado.", exception.getMessage());
    }

    @Test
    void findById_RestaurantExists() {
        Restaurant restaurant = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = restaurantUseCase.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
    }

    @Test
    void findById_RestaurantNotExists() {
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = restaurantUseCase.findById(1L);

        assertFalse(result.isPresent());
    }
}