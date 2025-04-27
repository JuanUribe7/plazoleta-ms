package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;


import com.example.plazoleta.ms_plazoleta.domain.ports.in.IDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.DishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.RestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {
    @Bean(name = "restaurantServicePort")
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort restaurantPersistencePort,
                                                         UserFeignClient userFeignClient) {
        return new RestaurantUseCase(restaurantPersistencePort, userFeignClient);
    }

    @Bean(name = "dishServicePort")
    public IDishServicePort dishServicePort(IDishPersistencePort dishPersistencePort,
                                            IRestaurantPersistencePort restaurantPersistencePort) {
        return new DishUseCase(dishPersistencePort, restaurantPersistencePort);
    }


}
