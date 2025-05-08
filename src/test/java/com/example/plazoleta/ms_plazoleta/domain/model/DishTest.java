package com.example.plazoleta.ms_plazoleta.domain.model;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishTest {

    private Dish baseDish;

    @BeforeEach
    void setUp() {
        baseDish = new Dish(
                1L,
                "Pizza",
                12000,
                "Tasty pizza",
                "http://img.com/pizza.jpg",
                true,
                100L,
                CategoryType.MAIN
        );
    }

    @Test
    void create_shouldCallValidatorsAndSaveDish() {
        RestaurantPersistencePort restaurantPort = mock(RestaurantPersistencePort.class);
        DishPersistencePort dishPort = mock(DishPersistencePort.class);

        when(dishPort.saveDish(baseDish)).thenReturn(baseDish);

        Dish created = baseDish.create(restaurantPort, dishPort, 50L);

        assertEquals(baseDish, created);
        verify(dishPort).saveDish(baseDish);
    }

    @Test
    void update_shouldCallValidatorsAndUpdateDish() {
        RestaurantPersistencePort restaurantPort = mock(RestaurantPersistencePort.class);
        DishPersistencePort dishPort = mock(DishPersistencePort.class);

        when(dishPort.updateDish(baseDish)).thenReturn(baseDish);

        Dish updated = baseDish.update(dishPort, restaurantPort, 1L, 50L);

        assertEquals(baseDish, updated);
        verify(dishPort).updateDish(baseDish);
    }

    @Test
    void changeStatus_shouldReturnNewDishWithUpdatedStatus() {
        RestaurantPersistencePort restaurantPort = mock(RestaurantPersistencePort.class);

        Dish updated = baseDish.changeStatus(false, baseDish.getRestaurantId(), 50L, restaurantPort);

        assertNotSame(baseDish, updated);
        assertFalse(updated.isActive());
        assertEquals(baseDish.getId(), updated.getId());
        assertEquals(baseDish.getName(), updated.getName());
    }

    @Test
    void changePrice_shouldUpdateWhenValid() {
        baseDish.changePrice(15000);
        assertEquals(15000, baseDish.getPrice());
    }

    @Test
    void changePrice_shouldThrowIfNullOrZero() {
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> baseDish.changePrice(null));
        assertEquals(ErrorFieldsMessages.DISH_PRICE_INVALID, ex1.getMessage());

        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> baseDish.changePrice(0));
        assertEquals(ErrorFieldsMessages.DISH_PRICE_INVALID, ex2.getMessage());
    }

    @Test
    void changeDescription_shouldUpdateWhenValid() {
        baseDish.changeDescription("Updated description");
        assertEquals("Updated description", baseDish.getDescription());
    }
}
