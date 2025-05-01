package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishUpdateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UpdateDishUseCaseTest {

    @Mock
    private DishPersistencePort dishPersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private UpdateDishUseCase updateDishUseCase;

    private Dish dish;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        dish = new Dish();
        dish.setId(1L);
        dish.setRestaurantId(10L);
        dish.setOwnerId(100L);

        restaurant = new Restaurant();
        restaurant.setId(10L);
        restaurant.setOwnerId(100L);
    }

    @Test
    void updateDish_success() {
        Dish foundDish = new Dish();
        foundDish.setName("ExistingDish");

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.updateDish(dish)).thenReturn(dish);

        try (MockedStatic<DishUpdateValidator> mockedValidator = mockStatic(DishUpdateValidator.class)) {
            mockedValidator.when(() -> DishUpdateValidator.validateDish(dish)).thenAnswer(invocation -> null);

            Dish result = updateDishUseCase.updateDish(dish, 1L);

            assertEquals(dish, result);
            verify(dishPersistencePort).updateDish(dish);
            verify(dishPersistencePort).findById(1L);
            verify(restaurantPersistencePort).findById(10L);
            mockedValidator.verify(() -> DishUpdateValidator.validateDish(dish));
        }
    }

    @Test
    void changeDishStatus_success() {
        Dish foundDish = new Dish();
        foundDish.setId(1L);
        foundDish.setRestaurantId(10L);

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.of(restaurant));

        updateDishUseCase.changeDishStatus(1L, 10L, 100L, true);

        assertTrue(foundDish.getActive());
        verify(dishPersistencePort).updateDish(foundDish);
    }

    @Test
    void changeDishStatus_dishNotFound_throwsException() {
        when(dishPersistencePort.findById(1L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.changeDishStatus(1L, 10L, 100L, true));
        assertEquals("Plato no encontrado", e.getMessage());
    }

    @Test
    void changeDishStatus_restaurantNotFound_throwsException() {
        Dish foundDish = new Dish();
        foundDish.setId(1L);
        foundDish.setRestaurantId(10L);

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.changeDishStatus(1L, 10L, 100L, true));
        assertEquals("Restaurante no encontrado", e.getMessage());
    }

    @Test
    void changeDishStatus_notOwner_throwsException() {
        Restaurant otherOwnerRestaurant = new Restaurant();
        otherOwnerRestaurant.setId(10L);
        otherOwnerRestaurant.setOwnerId(200L);

        Dish foundDish = new Dish();
        foundDish.setId(1L);
        foundDish.setRestaurantId(10L);

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.of(otherOwnerRestaurant));

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.changeDishStatus(1L, 10L, 100L, true));
        assertEquals("No eres propietario de este restaurante.", e.getMessage());
    }

    @Test
    void changeDishStatus_dishNotBelongToRestaurant_throwsException() {
        Dish foundDish = new Dish();
        foundDish.setId(1L);
        foundDish.setRestaurantId(20L);

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.of(restaurant));

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.changeDishStatus(1L, 10L, 100L, true));
        assertEquals("Este plato no pertenece a tu restaurante.", e.getMessage());
    }

    @Test
    void validate_dishNotFound_throwsException() {
        when(dishPersistencePort.findById(1L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.validate(dish, 1L));
        assertEquals("Plato no encontrado", e.getMessage());
    }

    @Test
    void validate_restaurantNotFound_throwsException() {
        Dish foundDish = new Dish();
        foundDish.setName("ExistingDish");

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.validate(dish, 1L));
        assertEquals("Restaurante no encontrado", e.getMessage());
    }

    @Test
    void validate_notOwner_throwsException() {
        Dish foundDish = new Dish();
        foundDish.setName("ExistingDish");

        Restaurant otherOwnerRestaurant = new Restaurant();
        otherOwnerRestaurant.setId(10L);
        otherOwnerRestaurant.setOwnerId(200L);

        when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(foundDish));
        when(restaurantPersistencePort.findById(10L)).thenReturn(Optional.of(otherOwnerRestaurant));

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                updateDishUseCase.validate(dish, 1L));
        assertEquals("No eres el propietario de este restaurante.", e.getMessage());
    }
}