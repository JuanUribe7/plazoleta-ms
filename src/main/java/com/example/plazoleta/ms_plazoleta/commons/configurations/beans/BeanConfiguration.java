package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;


import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.assign.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.UpdateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.assign.AssignEmployeeUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.create.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.create.CreateRestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.list.ListRestaurantsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {
    @Bean
    public CreateRestaurantServicePort restaurantServicePort(RestaurantPersistencePort restaurantPersistencePort,
                                                             UserValidationPort UserValidationPort) {
        return new CreateRestaurantUseCase(restaurantPersistencePort, UserValidationPort);
    }

    @Bean
    public CreateDishServicePort createDishServicePort(DishPersistencePort dishPersistencePort,
                                                       RestaurantPersistencePort restaurantPersistencePort) {
        return new CreateDishUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Bean
    public UpdateDishServicePort updateDishServicePort(DishPersistencePort dishPersistencePort,
                                                       RestaurantPersistencePort restaurantPersistencePort) {
        return new UpdateDishUseCase(dishPersistencePort, restaurantPersistencePort);
    }
    @Bean
    public ListRestaurantsServicePort listRestaurantsServicePort(RestaurantPersistencePort restaurantPersistencePort) {
        return new ListRestaurantsUseCase(restaurantPersistencePort);
    }

    @Bean
    public AssignEmployeeServicePort assignEmployeeServicePort(RestaurantPersistencePort restaurantPersistencePort
                                                           ) {
        return new AssignEmployeeUseCase(restaurantPersistencePort);
    }


}
