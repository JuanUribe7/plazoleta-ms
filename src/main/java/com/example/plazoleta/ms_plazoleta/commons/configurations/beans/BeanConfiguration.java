package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;


import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Pagination.RestaurantPaginationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Dish.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Dish.ListDishesUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Dish.UpdateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Restaurant.AssignEmployeeToRestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Restaurant.CreateRestaurantUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.Restaurant.ListRestaurantsUseCase;
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
    public ListDishesServicePort listDishServicePort(DishPersistencePort dishPersistencePort) {
        return new ListDishesUseCase(dishPersistencePort);
    }

    @Bean
    public AssignEmployeeServicePort assignEmployeeServicePort(RestaurantPersistencePort restaurantPersistencePort
                                                           ) {
        return new AssignEmployeeToRestaurantUseCase(restaurantPersistencePort);
    }


}
