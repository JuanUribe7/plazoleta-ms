package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;


import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Pagination.RestaurantPaginationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.ListDishesUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.UpdateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.AssignEmployeeToRestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.CreateRestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.ListRestaurantsUseCase;
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
    public ListRestaurantsServicePort listRestaurantsServicePort(RestaurantPersistencePort restaurantPersistencePort,
                                                                 RestaurantPaginationPort restaurantPaginationPort) {
        return new ListRestaurantsUseCase(restaurantPersistencePort, restaurantPaginationPort);
    }

    @Bean
    public ListDishesServicePort listDishServicePort(DishPersistencePort dishPersistencePort,
                                                     RestaurantPersistencePort restaurantPersistencePort) {
        return new ListDishesUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Bean
    public AssignEmployeeServicePort assignEmployeeServicePort(RestaurantPersistencePort restaurantPersistencePort
                                                           ) {
        return new AssignEmployeeToRestaurantUseCase(restaurantPersistencePort);
    }


}
