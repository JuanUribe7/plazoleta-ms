package com.example.plazoleta.ms_plazoleta.domain.usecases;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.CreateDishUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateDishUseCaseTest {

    private DishPersistencePort dishPort;
    private RestaurantPersistencePort restaurantPort;
    private CreateDishUseCase useCase;

    @BeforeEach
    void setUp() {
        dishPort = mock(DishPersistencePort.class);
        restaurantPort = mock(RestaurantPersistencePort.class);
        useCase = new CreateDishUseCase(dishPort, restaurantPort);
    }

    @Test
    void execute_shouldDelegateToDishCreateMethod() {
        // Arrange
        Dish inputDish = mock(Dish.class);
        Dish expectedDish = mock(Dish.class);
        Long ownerId = 99L;

        when(inputDish.create(restaurantPort, dishPort, ownerId)).thenReturn(expectedDish);

        // Act
        Dish result = useCase.execute(inputDish, ownerId);

        // Assert
        assertEquals(expectedDish, result);
        verify(inputDish).create(restaurantPort, dishPort, ownerId);
    }
}