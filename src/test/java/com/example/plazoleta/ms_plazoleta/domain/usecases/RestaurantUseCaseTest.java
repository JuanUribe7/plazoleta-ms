package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.OwnerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private IUserValidationPort userValidationPort;
    private RestaurantUseCase restaurantUseCase;

    private Restaurant sampleRestaurant;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userValidationPort = mock(IUserValidationPort.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userValidationPort);

        sampleRestaurant = new Restaurant(1L, "Resta", "24343235", "Calle 77 21Asur-06", "+3103479455", "http://logo.jpg", 10L);
    }

    @Test
    void createRestaurant_ShouldSaveSuccessfully() {
        when(userValidationPort.getRoleByUser(10L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("24343235")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findByName("Resta")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.saveRestaurant(sampleRestaurant)).thenReturn(sampleRestaurant);

        Restaurant result = restaurantUseCase.createRestaurant(sampleRestaurant);

        assertEquals(sampleRestaurant, result);
        verify(userValidationPort).updateOwnerRestaurantId(10L, 1L);
    }

    @Test
    void createRestaurant_ShouldThrow_WhenOwnerNotFound() {
        when(userValidationPort.getRoleByUser(10L)).thenThrow(new OwnerNotFoundException("Not found"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(sampleRestaurant));
        assertEquals("No existe un usuario con ese id.", ex.getMessage());
    }

    @Test
    void createRestaurant_ShouldThrow_WhenRoleIsNotOwner() {
        when(userValidationPort.getRoleByUser(10L)).thenReturn("ADMIN");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(sampleRestaurant));
        assertEquals("El id no corresponde a un usuario propietario.", ex.getMessage());
    }

    @Test
    void createRestaurant_ShouldThrow_WhenNitIsDuplicate() {
        when(userValidationPort.getRoleByUser(10L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("24343235")).thenReturn(Optional.of(sampleRestaurant));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(sampleRestaurant));
        assertEquals("El NIT del restaurante ya está registrado.", ex.getMessage());
    }

    @Test
    void createRestaurant_ShouldThrow_WhenNameIsDuplicate() {
        when(userValidationPort.getRoleByUser(10L)).thenReturn("OWNER");
        when(restaurantPersistencePort.findByNit("24343235")).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findByName("Resta")).thenReturn(Optional.of(sampleRestaurant));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(sampleRestaurant));
        assertEquals("El nombre del restaurante ya está registrado.", ex.getMessage());
    }

    @Test
    void isOwnerOfRestaurant_ShouldReturnTrue() {
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(sampleRestaurant));

        boolean result = restaurantUseCase.isOwnerOfRestaurant(1L, 10L);

        assertTrue(result);
    }

    @Test
    void isOwnerOfRestaurant_ShouldReturnFalse_WhenOwnerMismatch() {
        sampleRestaurant.setOwnerId(11L);
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(sampleRestaurant));

        boolean result = restaurantUseCase.isOwnerOfRestaurant(1L, 10L);

        assertFalse(result);
    }

    @Test
    void findById_ShouldReturnRestaurant_WhenExists() {
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(sampleRestaurant));

        Optional<Restaurant> result = restaurantUseCase.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleRestaurant, result.get());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = restaurantUseCase.findById(1L);

        assertTrue(result.isEmpty());
    }
}
