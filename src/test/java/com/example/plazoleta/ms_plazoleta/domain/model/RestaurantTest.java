package com.example.plazoleta.ms_plazoleta.domain.model;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant(
                1L,
                "La Pizzeria",
                "123456789",
                "Calle 1",
                "3001112233",
                "http://logo.png",
                10L,
                null
        );
    }

    @Test
    void create_shouldCallValidatorsAndSaveRestaurant() {
        RestaurantPersistencePort restaurantPort = mock(RestaurantPersistencePort.class);
        UserValidationPort userPort = mock(UserValidationPort.class);

        Restaurant result = restaurant.create(restaurantPort, userPort);

        assertEquals(restaurant, result);
        verify(restaurantPort).saveRestaurant(restaurant);
    }

    @Test
    void assignEmployee_shouldAddNewEmployeeAndSave() {
        RestaurantPersistencePort restaurantPort = mock(RestaurantPersistencePort.class);
        Long employeeId = 20L;

        Restaurant result = restaurant.assignEmployee(employeeId, restaurant.getOwnerId(), restaurantPort);

        assertEquals(restaurant, result);
        assertTrue(restaurant.getEmployeeIds().contains(employeeId));
        verify(restaurantPort).saveRestaurant(restaurant);
    }

    @Test
    void addEmployee_shouldThrowIfAlreadyAssigned() {
        restaurant.addEmployee(30L);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> restaurant.addEmployee(30L));
        assertEquals("Empleado ya asignado", ex.getMessage());
    }

    @Test
    void addEmployee_shouldAddIfNotExists() {
        restaurant.addEmployee(40L);
        assertTrue(restaurant.getEmployeeIds().contains(40L));
    }
}
