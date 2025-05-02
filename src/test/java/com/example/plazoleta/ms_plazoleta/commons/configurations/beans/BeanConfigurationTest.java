package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.CreateRestaurantUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    private BeanConfiguration beanConfiguration;
    private RestaurantPersistencePort restaurantPersistencePort;
    private DishPersistencePort dishPersistencePort;
    private UserValidationPort UserValidationPort;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration();
        restaurantPersistencePort = mock(RestaurantPersistencePort.class);
        dishPersistencePort = mock(DishPersistencePort.class);
        UserValidationPort = mock(UserValidationPort.class);
    }

    @Test
    void restaurantServicePortShouldReturnRestaurantUseCase() {
        var result = beanConfiguration.restaurantServicePort(restaurantPersistencePort, UserValidationPort); ;
        assertNotNull(result);
        assertTrue(result instanceof CreateRestaurantUseCase);
    }

    @Test
    void dishServicePortShouldReturnDishUseCase() {
        var result = beanConfiguration.createDishServicePort(dishPersistencePort, restaurantPersistencePort);
        assertNotNull(result);
        assertTrue(result instanceof CreateDishUseCase);
    }
}