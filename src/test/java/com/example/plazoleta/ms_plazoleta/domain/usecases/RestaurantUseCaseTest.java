
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantUseCaseTest {

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
    void saveRestaurant_ShouldReturnSavedRestaurant() {
        Restaurant restaurant = new Restaurant(1L, "Tasty Food", "12345678", "Main St 123", "+573001112233", "http://logo.com/img.png", 2L);
        when(userFeignClient.obtenerRolPorUsuario(2L)).thenReturn("OWNER");
        when(restaurantPersistencePort.saveRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant saved = restaurantUseCase.saveRestaurant(restaurant);

        assertNotNull(saved);
        assertEquals("Tasty Food", saved.getName());
        verify(userFeignClient).obtenerRolPorUsuario(2L);
        verify(restaurantPersistencePort).saveRestaurant(restaurant);
    }

    @Test
    void saveRestaurant_InvalidOwnerRole_ShouldThrowException() {
        Restaurant restaurant = new Restaurant(1L, "Tasty Food", "12345678", "Main St 123", "+573001112233", "http://logo.com/img.png", 3L);
        when(userFeignClient.obtenerRolPorUsuario(3L)).thenReturn("ADMIN");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            restaurantUseCase.saveRestaurant(restaurant);
        });

        assertEquals("El idPropietario no corresponde a un usuario propietario", thrown.getMessage());
        verify(userFeignClient).obtenerRolPorUsuario(3L);
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }
}
