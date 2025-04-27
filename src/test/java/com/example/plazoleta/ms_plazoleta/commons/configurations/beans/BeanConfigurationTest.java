package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.DishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.RestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    private BeanConfiguration beanConfiguration;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IDishPersistencePort dishPersistencePort;
    private UserValidationPort userValidationPort;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration();
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        dishPersistencePort = mock(IDishPersistencePort.class);
        userValidationPort = mock(UserValidationPort.class);
    }

    @Test
    void restaurantServicePortShouldReturnRestaurantUseCase() {
        var result = beanConfiguration.restaurantServicePort(restaurantPersistencePort, userValidationPort); ;
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