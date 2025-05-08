package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateDishUseCaseTest {

    private DishPersistencePort dishPort;
    private RestaurantPersistencePort restaurantPort;
    private UpdateDishUseCase useCase;

    @BeforeEach
    void setUp() {
        dishPort = mock(DishPersistencePort.class);
        restaurantPort = mock(RestaurantPersistencePort.class);
        useCase = new UpdateDishUseCase(dishPort, restaurantPort);
    }

    @Test
    void updateDish_shouldUpdateDescriptionAndPriceAndReturnUpdatedDish() {
        // Arrange
        Dish mockDish = mock(Dish.class);
        UpdateDishRequestDto dto = new UpdateDishRequestDto(1L, 2L, "Updated desc", 15000);

        when(dishPort.findById(1L)).thenReturn(Optional.of(mockDish));
        when(mockDish.update(dishPort, restaurantPort, 2L, 3L)).thenReturn(mockDish);

        // Act
        Dish result = useCase.updateDish(dto, 3L);

        // Assert
        assertEquals(mockDish, result);
        verify(mockDish).changeDescription("Updated desc");
        verify(mockDish).changePrice(15000);
        verify(mockDish).update(dishPort, restaurantPort, 2L, 3L);
    }

    @Test
    void updateDish_shouldThrowWhenDishNotFound() {
        UpdateDishRequestDto dto = new UpdateDishRequestDto(99L, 2L, "Desc", 12000);
        when(dishPort.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> useCase.updateDish(dto, 10L));

        assertEquals(ExceptionMessages.DISH_NOT_FOUND, ex.getMessage());
    }

    @Test
    void changeDishStatus_shouldCallChangeStatusAndUpdateDish() {
        Dish mockDish = mock(Dish.class);
        Dish updatedDish = mock(Dish.class);

        when(dishPort.findById(1L)).thenReturn(Optional.of(mockDish));
        when(mockDish.changeStatus(true, 2L, 3L, restaurantPort)).thenReturn(updatedDish);

        useCase.changeDishStatus(1L, 2L, 3L, true);

        verify(mockDish).changeStatus(true, 2L, 3L, restaurantPort);
        verify(dishPort).updateDish(updatedDish);
    }

    @Test
    void changeDishStatus_shouldThrowIfDishNotFound() {
        when(dishPort.findById(123L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> useCase.changeDishStatus(123L, 1L, 1L, false));

        assertEquals(ExceptionMessages.DISH_NOT_FOUND, ex.getMessage());
    }
}
