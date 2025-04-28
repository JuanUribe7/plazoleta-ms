package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;


import com.example.plazoleta.ms_plazoleta.domain.ports.in.ICreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IUpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.RestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.UpdateDishUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {
    @Bean(name = "restaurantServicePort")
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort restaurantPersistencePort,
                                                        UserValidationPort userValidationPort) {
        return new RestaurantUseCase(restaurantPersistencePort, userValidationPort);
    }

    @Bean(name = "createDishServicePort")
    public ICreateDishServicePort createDishServicePort(IDishPersistencePort dishPersistencePort,
                                                        IRestaurantPersistencePort restaurantPersistencePort) {
        return new CreateDishUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Bean(name = "updateDishServicePort")
    public IUpdateDishServicePort updateDishServicePort(IDishPersistencePort dishPersistencePort,
                                                        IRestaurantPersistencePort restaurantPersistencePort) {
        return new UpdateDishUseCase(dishPersistencePort, restaurantPersistencePort);
    }


}
