package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.DishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.RestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    private BeanConfiguration beanConfiguration;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IDishPersistencePort dishPersistencePort;
    private UserFeignClient userFeignClient;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration();
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        dishPersistencePort = mock(IDishPersistencePort.class);
        userFeignClient = mock(UserFeignClient.class);
    }

    @Test
    void restaurantServicePortShouldReturnRestaurantUseCase() {
        var result = beanConfiguration.restaurantServicePort(restaurantPersistencePort, userFeignClient);
        assertNotNull(result);
        assertTrue(result instanceof RestaurantUseCase);
    }

    @Test
    void dishServicePortShouldReturnDishUseCase() {
        var result = beanConfiguration.dishServicePort(dishPersistencePort, restaurantPersistencePort);
        assertNotNull(result);
        assertTrue(result instanceof DishUseCase);
    }
}