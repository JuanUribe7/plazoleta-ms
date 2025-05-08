package com.example.plazoleta.ms_plazoleta.domain.usecases;


import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.CreateRestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateRestaurantUseCaseTest {

    private RestaurantPersistencePort restaurantPort;
    private com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort userValidationPort;
    private CreateRestaurantUseCase useCase;

    @BeforeEach
    void setUp() {
        restaurantPort = mock(RestaurantPersistencePort.class);
        userValidationPort = mock(com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort.class);
        useCase = new CreateRestaurantUseCase(restaurantPort, userValidationPort);
    }

    @Test
    void execute_shouldCallCreateMethodAndReturnResult() {
        // Arrange
        Restaurant inputRestaurant = mock(Restaurant.class);
        Restaurant expectedRestaurant = mock(Restaurant.class);
        when(inputRestaurant.create(restaurantPort, userValidationPort)).thenReturn(expectedRestaurant);

        // Act
        Restaurant result = useCase.execute(inputRestaurant);

        // Assert
        assertEquals(expectedRestaurant, result);
        verify(inputRestaurant, times(1)).create(restaurantPort, userValidationPort);
    }
}
