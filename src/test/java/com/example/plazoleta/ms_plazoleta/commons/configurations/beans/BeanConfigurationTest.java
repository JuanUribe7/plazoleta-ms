package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.RestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.UpdateDishUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    private BeanConfiguration beanConfiguration;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IDishPersistencePort    dishPersistencePort;
    private IUserValidationPort     userValidationPort;

    @BeforeEach
    void setUp() {
        beanConfiguration            = new BeanConfiguration();
        restaurantPersistencePort    = mock(IRestaurantPersistencePort.class);
        dishPersistencePort          = mock(IDishPersistencePort.class);
        userValidationPort           = mock(IUserValidationPort.class);
    }

    @Test
    void restaurantServicePortShouldReturnRestaurantUseCase() {
        var result = beanConfiguration.restaurantServicePort(
                restaurantPersistencePort,
                userValidationPort
        );
        assertNotNull(result);
        assertTrue(result instanceof RestaurantUseCase);
    }

    @Test
    void createDishServicePortShouldReturnCreateDishUseCase() {
        var result = beanConfiguration.createDishServicePort(
                dishPersistencePort,
                restaurantPersistencePort,
                userValidationPort
        );
        assertNotNull(result);
        assertTrue(result instanceof CreateDishUseCase);
    }

    @Test
    void updateDishServicePortShouldReturnUpdateDishUseCase() {
        var result = beanConfiguration.updateDishServicePort(
                dishPersistencePort,
                restaurantPersistencePort,
                userValidationPort
        );
        assertNotNull(result);
        assertTrue(result instanceof UpdateDishUseCase);
    }
}
