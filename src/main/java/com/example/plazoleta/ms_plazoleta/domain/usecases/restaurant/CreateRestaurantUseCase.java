package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;

public class CreateRestaurantUseCase implements CreateRestaurantServicePort {

    private final RestaurantPersistencePort restaurantPort;
    private final UserValidationPort userValidationPort;

    public CreateRestaurantUseCase(RestaurantPersistencePort restaurantPort,
                                   UserValidationPort userValidationPort) {
        this.restaurantPort = restaurantPort;
        this.userValidationPort = userValidationPort;
    }

    public Restaurant execute(Restaurant restaurant) {
        return restaurant.create(restaurantPort, userValidationPort);
    }


}
